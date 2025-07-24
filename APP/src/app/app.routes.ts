import { Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ProfessoresPageComponent } from './components/professores-page/professores-page.component';
import { DisciplinasPageComponent } from './components/disciplinas-page/disciplinas-page.component';
import { MatriculasPageComponent } from './components/matriculas-page/matriculas-page.component';

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

    // Rota para a página disciplinas
    { path: 'disciplinas', component: DisciplinasPageComponent },

    // Rota para a página matrículas
    { path: 'matriculas', component: MatriculasPageComponent },

    // Rota para páginas não encontradas
    { path: '**', redirectTo: '/login' }
];
