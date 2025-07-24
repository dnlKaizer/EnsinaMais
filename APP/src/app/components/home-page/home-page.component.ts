import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  isAdmin: boolean = false;
  isProfessor: boolean = false;
  isAluno: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    this.isAdmin = authService.isAdmin();
    this.isProfessor = authService.isProfessor();
    this.isAluno = authService.isAluno();
  }

  /**
   * Executa o logout do usuário e redireciona para a página de login
   */
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  goToAlunosPage() {
    this.router.navigate(['/alunos']);
  }

  goToProfessoresPage() {
    this.router.navigate(['/professores']);
  }

  goToTurmasPage() {
    this.router.navigate(['/turmas']);
  }

  goToDisciplinasPage() {
    this.router.navigate(['/disciplinas']);
  }

  goToNotasPage() {
    this.router.navigate(['']);
  }

  goToAvaliacoesPage() {
    this.router.navigate(['']);
  }

  goToMatriculasPage() {
    this.router.navigate(['/matriculas']);
  }

  goToDesempenhoPage() {
    this.router.navigate(['']);
  }

}
