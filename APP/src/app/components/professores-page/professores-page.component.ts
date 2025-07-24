import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

// Interface para os dados do professor
interface Professor {
  id: number;
  nome: string;
  cpf: string;
  email: string;
  titulacao: string;
}

@Component({
  selector: 'app-professores-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './professores-page.component.html',
  styleUrl: './professores-page.component.css'
})
export class ProfessoresPageComponent {

  professores: Professor[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';

  // Propriedades do modal
  showModal: boolean = false;
  selectedProfessor: Professor | null = null;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  /**
   * Método executado quando o componente é inicializado
   */
  async ngOnInit() {
    await this.carregarProfessores();
  }

  /**
   * 
   */ 
  async adicionarProfessor() {

  }

  /**
   * Busca a lista de professores na API
   */
  async carregarProfessores(): Promise<void> {
    this.isLoading = true;
    this.errorMessage = '';

    try {
      // Fazer requisição GET autenticada
      const response = await this.authService.authenticatedFetch('/professores');
      
      // Verificar se a resposta foi bem-sucedida
      if (!response.ok) {
        throw new Error(`Erro ${response.status}: ${response.statusText}`);
      }

      // Converter resposta para JSON
      this.professores = await response.json();

      console.log('Professores carregados:', this.professores);

    } catch (error: any) {
      console.error('Erro ao carregar professores:', error);
      this.errorMessage = error.message || 'Erro ao carregar dados';
    } finally {
      this.isLoading = false;
    }
  }

  /**
   * Recarrega a lista de professores
   */
  async recarregar(): Promise<void> {
    await this.carregarProfessores();
  }

  /**
   * Exclui um professor
   */
  async excluirProfessor(professorId: number): Promise<void> {
    if (!confirm('Tem certeza que deseja excluir este professor?')) {
      return;
    }

    try {
      const response = await this.authService.authenticatedFetch(`/professores/${professorId}`, {
        method: 'DELETE'
      });

      if (!response.ok) {
        throw new Error('Erro ao excluir professor');
      }

      // Remover da lista local
      this.professores = this.professores.filter(prof => prof.id !== professorId);
      console.log('Professor excluído com sucesso');

    } catch (error: any) {
      console.error('Erro ao excluir professor:', error);
      this.errorMessage = 'Erro ao excluir professor';
    }
  }

  /**
   * Edita um professor
   */
  editarProfessor(professorId: number): void {
    // Navegar para página de edição ou abrir modal
    this.router.navigate(['/professores/editar', professorId]);
  }

  /**
   * Executa o logout do usuário e redireciona para a página de login
   */
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
  
  home(): void {
    this.router.navigate(['/home']);
  }

  /**
   * Abre o modal com os detalhes do professor
   */
  abrirModal(professor: Professor): void {
    this.selectedProfessor = professor;
    this.showModal = true;
  }

  /**
   * Fecha o modal
   */
  fecharModal(): void {
    this.showModal = false;
    this.selectedProfessor = null;
  }

  /**
   * Gera as iniciais do nome para o avatar
   */
  getInitials(nome: string): string {
    if (!nome) return 'P';
    
    const palavras = nome.trim().split(' ');
    if (palavras.length === 1) {
      return palavras[0].charAt(0).toUpperCase();
    }
    
    return (palavras[0].charAt(0) + palavras[palavras.length - 1].charAt(0)).toUpperCase();
  }
}
