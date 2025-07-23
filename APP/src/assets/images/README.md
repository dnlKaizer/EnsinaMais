# Assets - Imagens

Esta pasta contém todas as imagens do projeto Ensina+.

## Estrutura:

- `logos/` - Logotipos e marcas
- `icons/` - Ícones e pictogramas  
- `backgrounds/` - Imagens de fundo
- `avatars/` - Fotos de perfil (se houver)

## Como usar:

```html
<!-- No template HTML -->
<img src="assets/images/logos/logo.png" alt="Logo">

<!-- Com binding -->
<img [src]="imagePath" alt="Imagem dinâmica">
```

```css
/* No CSS */
.header {
  background-image: url('assets/images/backgrounds/header-bg.jpg');
}
```

## Formatos recomendados:

- **PNG** - Para logotipos e ícones com transparência
- **JPG** - Para fotos e imagens sem transparência  
- **SVG** - Para ícones vetoriais (recomendado)
- **WebP** - Para imagens otimizadas (navegadores modernos)
