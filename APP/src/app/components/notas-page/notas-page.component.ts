import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Interface para os dados da Nota
interface Nota {
  id: number;
  nota: number;
  idAvaliacao: number;
  idMatriculaTurma: number;
}

// Enum para os modos do modal
enum ModalMode {
  VIEW = 'view',
  EDIT = 'edit',
  CREATE = 'create'
}

@Component({
  selector: 'app-notas-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './notas-page.component.html',
  styleUrl: './notas-page.component.css'
})
export class NotasPageComponent {
  notas: Nota[] = [];
    isLoading: boolean = false;
    errorMessage: string = '';
  
    // Propriedades do modal
    showModal: boolean = false;
    selectedNota: Nota | null = null;
    modalMode: ModalMode = ModalMode.VIEW;
    
    // Enum para usar no template
    ModalMode = ModalMode;
    
    // Dados do formulário
    formData: Nota = {
      id: 0,
      nota: 0,
      idAvaliacao: 0,
      idMatriculaTurma: 0
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
     * Adiciona uma novo nota
     */ 
    async inserir() {
      this.abrirModalCriacao();
    }
  
    /**
     * Busca a lista de notas na API
     */
    async carregar(): Promise<void> {
      this.isLoading = true;
      this.errorMessage = '';
  
      try {
        // Fazer requisição GET autenticada
        const response = await this.authService.authenticatedFetch('/notas');
        
        // Verificar se a resposta foi bem-sucedida
        if (!response.ok) {
          throw new Error(`Erro ${response.status}: ${response.statusText}`);
        }
  
        // Converter resposta para JSON
        this.notas = await response.json();
  
        console.log('Notas carregadas:', this.notas);
  
      } catch (error: any) {
        console.error('Erro ao carregar notas:', error);
        this.errorMessage = error.message || 'Erro ao carregar dados';
      } finally {
        this.isLoading = false;
      }
    }
  
    /**
     * Recarrega a lista de notas
     */
    async recarregar(): Promise<void> {
      await this.carregar();
    }
  
    /**
     * Exclui uma nota
     */
    async excluir(notaId: number): Promise<void> {
      if (!confirm('Tem certeza que deseja excluir esta nota?')) {
        return;
      }
  
      try {
        const response = await this.authService.authenticatedFetch(`/notas/${notaId}`, {
          method: 'DELETE'
        });
  
        if (!response.ok) {
          throw new Error('Erro ao excluir nota');
        }
  
        // Remover da lista local
        this.notas = this.notas.filter(nota => nota.id !== notaId);
        console.log('Nota excluída com sucesso');
  
      } catch (error: any) {
        console.error('Erro ao excluir nota:', error);
        this.errorMessage = 'Erro ao excluir nota';
      }
    }
  
    /**
     * Edita uma nota
     */
    editar(notaId: number): void {
      const nota = this.notas.find(n => n.id === notaId);
      if (nota) {
        this.abrirModalEdicao(nota);
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
     * Abre o modal com os detalhes da nota (modo visualização)
     */
    abrirModal(nota: Nota): void {
      this.selectedNota = nota;
      this.modalMode = ModalMode.VIEW;
      this.showModal = true;
    }
  
    /**
     * Abre o modal para criação de nova nota
     */
    abrirModalCriacao(): void {
      this.selectedNota = null;
      this.modalMode = ModalMode.CREATE;
      this.formData = {
        id: 0,
        nota: 0,
        idAvaliacao: 0,
        idMatriculaTurma: 0
      };
      this.showModal = true;
    }
  
    /**
     * Abre o modal para edição de nota
     */
    abrirModalEdicao(nota: Nota): void {
      this.selectedNota = nota;
      this.modalMode = ModalMode.EDIT;
      this.formData = { ...nota }; // Cópia dos dados para edição
      this.showModal = true;
    }
  
    /**
     * Fecha o modal
     */
    fecharModal(): void {
      this.showModal = false;
      this.selectedNota = null;
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
        console.error('Erro ao salvar nota:', error);
        this.errorMessage = error.message || 'Erro ao salvar nota';
      } finally {
        this.isSubmitting = false;
      }
    }
  
    /**
     * Cria uma nova nota
     */
    private async create(): Promise<void> {
      const response = await this.authService.authenticatedFetch('/notas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          nota: this.formData.nota,
          idAvaliacao: this.formData.idAvaliacao,
          idMatriculaTurma: this.formData.idMatriculaTurma,
        })
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || 'Erro ao criar nota');
      }
    }
  
    /**
     * Atualiza uma nota existente
     */
    private async atualizar(): Promise<void> {
      const response = await this.authService.authenticatedFetch(`/notas/${this.formData.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          nota: this.formData.nota,
          idAvaliacao: this.formData.idAvaliacao,
          idMatriculaTurma: this.formData.idMatriculaTurma,
        })
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || 'Erro ao atualizar nota');
      }
    }
  
    /**
     * Valida os dados do formulário
     */
    private validarFormulario(): boolean {
      if (this.formData.nota <= 0) {
        this.errorMessage = 'Nota é obrigatória';
        return false;
      }
      if (this.formData.idAvaliacao <= 0) {
        this.errorMessage = 'Id da avaliação é obrigatório';
        return false;
      }
      if (this.formData.idMatriculaTurma <= 0) {
        this.errorMessage = 'Id de MatriculaTurma é obrigatório';
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
          return 'Detalhes da Nota';
        case ModalMode.EDIT:
          return 'Editar Nota';
        case ModalMode.CREATE:
          return 'Novo Nota';
        default:
          return 'Nota';
      }
    }
}
