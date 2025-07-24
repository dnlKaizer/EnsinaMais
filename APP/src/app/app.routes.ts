import { Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ProfessoresPageComponent } from './components/professores-page/professores-page.component';
import { AlunosPageComponent } from './components/alunos-page/alunos-page.component';

export const routes: Routes = [
  // Rota padrão - redireciona para login
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Rota para a página de login
  { path: 'login', component: LoginPageComponent },

  // Rota para a página inicial/home
  { path: 'home', component: HomePageComponent },
  { path: 'dashboard', component: HomePageComponent }, // Alternativa

  // Rota para a página professores
  { path: 'professores', component: ProfessoresPageComponent },

  // Rota para a página alunos
  { path: 'alunos', component: AlunosPageComponent },

  // Rota para páginas não encontradas
  { path: '**', redirectTo: '/login' },
];
