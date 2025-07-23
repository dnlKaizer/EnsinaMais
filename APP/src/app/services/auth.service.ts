import { Injectable } from '@angular/core';

/**
 * Interface para os dados de requisição de login
 */
export interface LoginRequest {
    login: string;
    senha: string;
}

/**
 * Interface para a resposta do login da API
 */
export interface LoginResponse {
    acessToken: string;
    tokenType: string;
}

/**
 * Serviço responsável por gerenciar a autenticação do usuário
 * Inclui login, logout, armazenamento de token e requisições autenticadas
 */
@Injectable({
    providedIn: 'root'
})
export class AuthService {
    // URL base da API backend
    private apiUrl = 'http://localhost:8080';

    constructor() { }

    /**
     * Realiza o login do usuário na API
     * @param loginData - Dados de login (usuario e senha)
     * @returns Promise com o acessToken e tokenType
     * @throws Error se as credenciais forem inválidas ou houver erro de rede
     */
    async login(loginData: LoginRequest): Promise<LoginResponse> {
        // Faz a requisição POST para o endpoint de login
        const response = await fetch(`${this.apiUrl}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8',
            },
            body: JSON.stringify(loginData)
        });

        // Verifica se a resposta foi bem-sucedida
        if (!response.ok) {
            // Tenta extrair a mensagem de erro da resposta
            const errorData = await response.json().catch(() => ({}));
            throw new Error(errorData.message || 'Credenciais inválidas');
        }

        // Retorna os dados da resposta (token e usuário)
        return await response.json();
    }

    /**
     * Armazena o token JWT no localStorage do navegador
     * @param token - Token JWT recebido da API
     */
    saveToken(token: string): void {
        localStorage.setItem('acessToken', token);
    }

    /**
     * Armazena o tipo do token no localStorage
     * @param tokenType - Tipo do token (geralmente "Bearer")
     */
    saveTokenType(tokenType: string): void {
        localStorage.setItem('tokenType', tokenType);
    }

    /**
     * Armazena os dados do usuário no localStorage
     * @param user - Objeto com dados do usuário
     */
    saveUser(user: any): void {
        localStorage.setItem('user', JSON.stringify(user));
    }

    /**
     * Recupera o token JWT armazenado no localStorage
     * @returns Token JWT ou null se não existir
     */
    getToken(): string | null {
        return localStorage.getItem('acessToken');
    }

    /**
     * Recupera o tipo do token armazenado no localStorage
     * @returns Tipo do token ou null se não existir
     */
    getTokenType(): string | null {
        return localStorage.getItem('tokenType');
    }

    /**
     * Recupera os dados do usuário armazenados no localStorage
     * @returns Objeto com dados do usuário ou null se não existir
     */
    getUser(): any {
        const user = localStorage.getItem('user');
        return user ? JSON.parse(user) : null;
    }

    /**
     * Obtém o header de autorização completo
     * @returns String no formato "Bearer token" ou null se não houver token
     */
    getAuthorizationHeader(): string | null {
        const token = this.getToken();
        const tokenType = this.getTokenType() || 'Bearer';
        return token ? `${tokenType} ${token}` : null;
    }

    /**
     * Verifica se o usuário está logado (possui token válido)
     * @returns true se estiver logado, false caso contrário
     */
    isLoggedIn(): boolean {
        return !!this.getToken();
    }

    /**
     * Realiza o logout removendo token e dados do usuário
     */
    logout(): void {
        localStorage.removeItem('acessToken');
        localStorage.removeItem('tokenType');
        localStorage.removeItem('user');
    }

    /**
     * Método utilitário para fazer requisições autenticadas à API
     * Adiciona automaticamente o token JWT no header Authorization
     * @param url - Endpoint da API (sem a URL base)
     * @param options - Opções da requisição fetch
     * @returns Promise com a resposta da requisição
     * 
     * @example
     * // GET request
     * const response = await authService.authenticatedFetch('/usuarios');
     * 
     * // POST request
     * const response = await authService.authenticatedFetch('/usuarios', {
     *   method: 'POST',
     *   body: JSON.stringify(userData)
     * });
     */
    async authenticatedFetch(url: string, options: RequestInit = {}): Promise<Response> {
        // Obtém o token e tipo do token armazenados
        const token = this.getToken();
        const tokenType = this.getTokenType() || 'Bearer';

        // Prepara os headers padrão
        const headers: Record<string, string> = {
            'Content-Type': 'application/json',
            // Mescla com headers customizados passados como parâmetro
            ...(options.headers as Record<string, string>),
        };

        // Adiciona o token de autorização se estiver disponível
        if (token) {
            headers['Authorization'] = `${tokenType} ${token}`;
        }

        // Faz a requisição com a URL completa e headers atualizados
        return fetch(`${this.apiUrl}${url}`, {
            ...options,
            headers
        });
    }
}
