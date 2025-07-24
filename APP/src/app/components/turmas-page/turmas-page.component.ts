import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Interface para os dados da turma
interface Turma {
  id: number;
  semestre: string;
  vagas: number;
  idDisciplina: number;
  idProfessor: number;
}

// Enum para os modos do modal
enum ModalMode {
  VIEW = 'view',
  EDIT = 'edit',
  CREATE = 'create'
}

@Component({
  selector: 'app-turmas-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './turmas-page.component.html',
  styleUrl: './turmas-page.component.css'
})
export class TurmasPageComponent {

  turmas: Turma[] = [];
    isLoading: boolean = false;
    errorMessage: string = '';
  
    // Propriedades do modal
    showModal: boolean = false;
    selectedTurma: Turma | null = null;
    modalMode: ModalMode = ModalMode.VIEW;
    
    // Enum para usar no template
    ModalMode = ModalMode;
    
    // Dados do formulário
    formData: Turma = {
      id: 0,
      semestre: '',
      vagas: 0,
      idDisciplina: 0,
      idProfessor: 0
    };
    
    // Estado do formulário
    isSubmitting: boolean = false;
  
    constructor(
      private authService: AuthService,
      private router: Router
    ) {}
  
    /**
     * Método executado quando o componente é inicializado
     */
    async ngOnInit() {
      await this.carregar();
    }
  
    /**
     * Adiciona uma nova turma
     */ 
    async inserir() {
      this.abrirModalCriacao();
    }
  
    /**
     * Busca a lista de turmas na API
     */
    async carregar(): Promise<void> {
      this.isLoading = true;
      this.errorMessage = '';
  
      try {
        // Fazer requisição GET autenticada
        const response = await this.authService.authenticatedFetch('/turmas');
        
        // Verificar se a resposta foi bem-sucedida
        if (!response.ok) {
          throw new Error(`Erro ${response.status}: ${response.statusText}`);
        }
  
        // Converter resposta para JSON
        this.turmas = await response.json();
  
        console.log('turmas carregados:', this.turmas);
  
      } catch (error: any) {
        console.error('Erro ao carregar turmas:', error);
        this.errorMessage = error.message || 'Erro ao carregar dados';
      } finally {
        this.isLoading = false;
      }
    }
  
    /**
     * Recarrega a lista de turmas
     */
    async recarregar(): Promise<void> {
      await this.carregar();
    }
  
    /**
     * Exclui uma turma
     */
    async excluir(turmaId: number): Promise<void> {
      if (!confirm('Tem certeza que deseja excluir esta turma?')) {
        return;
      }
  
      try {
        const response = await this.authService.authenticatedFetch(`/turmas/${turmaId}`, {
          method: 'DELETE'
        });
  
        if (!response.ok) {
          throw new Error('Erro ao excluir turma');
        }
  
        // Remover da lista local
        this.turmas = this.turmas.filter(turma => turma.id !== turmaId);
        console.log('Turma excluída com sucesso');
  
      } catch (error: any) {
        console.error('Erro ao excluir turma:', error);
        this.errorMessage = 'Erro ao excluir turma';
      }
    }
  
    /**
     * Edita uma turma
     */
    editar(turmaId: number): void {
      const turma = this.turmas.find(p => p.id === turmaId);
      if (turma) {
        this.abrirModalEdicao(turma);
      }
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
     * Abre o modal com os detalhes do turma (modo visualização)
     */
    abrirModal(turma: Turma): void {
      this.selectedTurma = turma;
      this.modalMode = ModalMode.VIEW;
      this.showModal = true;
    }
  
    /**
     * Abre o modal para criação de novo turma
     */
    abrirModalCriacao(): void {
      this.selectedTurma = null;
      this.modalMode = ModalMode.CREATE;
      this.formData = {
        id: 0,
        semestre: '',
        vagas: 0,
        idDisciplina: 0,
        idProfessor: 0
      };
      this.showModal = true;
    }
  
    /**
     * Abre o modal para edição de turma
     */
    abrirModalEdicao(turma: Turma): void {
      this.selectedTurma = turma;
      this.modalMode = ModalMode.EDIT;
      this.formData = { ...turma }; // Cópia dos dados para edição
      this.showModal = true;
    }
  
    /**
     * Fecha o modal
     */
    fecharModal(): void {
      this.showModal = false;
      this.selectedTurma = null;
      this.modalMode = ModalMode.VIEW;
      this.isSubmitting = false;
      this.errorMessage = '';
    }
  
    /**
     * Salva os dados do formulário (criar ou editar)
     */
    async salvar(): Promise<void> {
      if (!this.validarFormulario()) {
        return;
      }
  
      this.isSubmitting = true;
      this.errorMessage = '';
  
      try {
        if (this.modalMode === ModalMode.CREATE) {
          await this.create();
        } else if (this.modalMode === ModalMode.EDIT) {
          await this.atualizar();
        }
        
        this.fecharModal();
        await this.carregar(); // Recarrega a lista
        
      } catch (error: any) {
        console.error('Erro ao salvar turma:', error);
        this.errorMessage = 'Erro ao salvar turma: ' + error.message;
      } finally {
        this.isSubmitting = false;
      }
    }
  
    /**
     * Cria uma nova turma
     */
    private async create(): Promise<void> {
      const response = await this.authService.authenticatedFetch('/turmas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          semestre: this.formData.semestre,
          vagas: this.formData.vagas,
          idDisciplina: this.formData.idDisciplina,
          idProfessor: this.formData.idProfessor
        })
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || 'Erro ao criar turma');
      }
    }
  
    /**
     * Atualiza uma turma existente
     */
    private async atualizar(): Promise<void> {
      const response = await this.authService.authenticatedFetch(`/turmas/${this.formData.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          semestre: this.formData.semestre,
          vagas: this.formData.vagas,
          idDisciplina: this.formData.idDisciplina,
          idProfessor: this.formData.idProfessor
        })
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || 'Erro ao atualizar turma');
      }
    }
  
    /**
     * Valida os dados do formulário
     */
    private validarFormulario(): boolean {
      if (!this.formData.semestre.trim()) {
        this.errorMessage = 'Semestre é obrigatório';
        return false;
      }
      if (this.formData.vagas <= 0) {
        this.errorMessage = 'Vagas são obrigatórias';
        return false;
      }
      if (this.formData.idDisciplina == 0) {
        this.errorMessage = 'Id da disciplina é obrigatório';
        return false;
      }
      if (this.formData.idProfessor == 0) {
        this.errorMessage = 'Id do professor é obrigatório';
        return false;
      }
      return true;
    }
  
    /**
     * Retorna o título do modal baseado no modo
     */
    getModalTitle(): string {
      switch (this.modalMode) {
        case ModalMode.VIEW:
          return 'Detalhes da Turma';
        case ModalMode.EDIT:
          return 'Editar Turma';
        case ModalMode.CREATE:
          return 'Novo Turma';
        default:
          return 'Turma';
      }
    }
}
