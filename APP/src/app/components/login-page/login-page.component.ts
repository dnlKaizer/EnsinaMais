import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  login: string = "";
  senha: string = "";
  isLoading: boolean = false;
  errorMessage: string = "";

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  // Função de autenticar Login
  async autenticar() {
    this.isLoading = true;
    this.errorMessage = "";

    try {
      const response = await this.authService.login({
        login: this.login,
        senha: this.senha
      });

      // Armazenar o token e tipo do token
      this.authService.saveToken(response.acessToken);
      this.authService.saveTokenType(response.tokenType);
      
      // Salvar dados básicos do usuário (já que não vem na resposta)
      this.authService.saveUser({
        login: this.login,
        // Outros dados podem ser obtidos posteriormente via API
      });

      // Redirecionar para a página home
      this.router.navigate(['/home']);
      
      console.log('Login realizado com sucesso!', response);
      alert(`Login realizado com sucesso! Bem-vindo, ${this.login}!`);
      
    } catch (error: any) {
      console.error('Erro ao fazer login:', error);
      this.errorMessage = 'Erro de conexão. Verifique se a API está rodando.';
    } finally {
      this.isLoading = false;
    }
  }

  // Função para limpar mensagens de erro quando o usuário digitar
  clearError() {
    this.errorMessage = "";
  }
}