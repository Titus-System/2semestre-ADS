# Commits e processo de contribuição

## Padrão de Commits

Estamos utilizando o formato **Conventional Commits** para garantir consistência no histórico de commits. Isso facilita a compreensão das mudanças.

### Formato do Commit

Cada commit deve seguir o seguinte formato:

>
>\<tipo>(\<escopo>): \<descrição curta>
>
>[Mensagem opcional] Explicação mais detalhada (caso necessário).
>

- **`<tipo>`**: Indica a natureza do commit. Exemplos:
  - `feat`: Adição de uma nova funcionalidade.
  - `fix`: Correção de um bug.
  - `docs`: Alterações na documentação (somente).
  - `style`: Alterações de formatação que não afetam o código (ex: espaçamento).
  - `refactor`: Refatoração sem alterar a funcionalidade.
  - `test`: Adição ou alteração de testes.
  - `chore`: Alterações que não se encaixam nas categorias acima (ex: mudanças de dependências).

- **`<escopo>`**: Indica qual parte do código foi alterada (ex: `ollama`, `gui`, `database`, etc).

- **`<descrição curta>`**: Uma descrição clara e objetiva da mudança.

### Exemplos

- `feat(database): adiciona conexão com o banco de dados mysql`
- `fix(ollamaConnection): corrige erro na abertura da conexão com Ollama`
- `docs(README): adiciona seção sobre configuração inicial`
- `style(ui): ajusta espaçamento entre botões`

## Processo de Contribuição

Os membros da equipe podem dar push diretamente na branch correspondente à sprint em andamento.

### Workflow com Branches

Cada sprint terá sua própria branch, e todos os membros da equipe devem trabalhar na branch da sprint correspondente. Nome das branches para cada sprint:

- **Sprint 1**: `sprint-1`
- **Sprint 2**: `sprint-2`
- **Sprint 3**: `sprint-3`
- **Sprint 4**: `sprint-4`

## Trabalhe na branch correta

Conforme especificado em aula, cada Sprint deverá ter sua própria branch e todos os commits deverão ser feitos na branch correspondente à Sprint em andamento.

Siga os passos abaixo para garantir que você está trabalhando na branch correta do repositório.

### 1. Listar as Branches Existentes

No terminal, dentro do diretório do repositório, execute o comando abaixo para listar todas as branches disponíveis, incluindo as branches remotas:

```git
git branch -a
```

Isso mostrará todas as branches locais e remotas. A que tiver um * ao lado é a branch em que você está.

### 2. Criar e Mudar para a Branch da Sprint

Para começar a trabalhar na branch da sprint correta (por exemplo, Sprint 1), crie uma branch local a partir da branch remota correspondente:

```git
git checkout -b sprint-1 origin/sprint-1
```

Este comando cria uma cópia local da branch remota `sprint-1` e muda para ela automaticamente.

### 3. Confirme que está na branch correta

Para verificar em qual branch você está, execute o seguinte comando:

```git
git branch
```

A branch atual aparecerá com um * ao lado. Se a branch sprint-1 estiver marcada, você está no lugar certo.

Para alterar a branch em que você está:

```git
git checkout nome-da-branch
```

### 4. Fazer alterações e commits

Depois de garantir que está trabalhando na branch correta, faça suas alterações e commits conforme o padrão anteriormente especificado:

```git
git add .
git commit -m "feat: adiciona nova funcionalidade na sprint-1"
```

### 5. Enviar as Mudanças para a Branch Remota

Após concluir suas alterações, faça o push para a branch remota da sprint:

```git
git push origin sprint-1
```

## Boas Práticas

- **Comente o Código**: Inclua comentários explicando partes complexas do código.
- **Mantenha o Código Limpo**: Siga padrões de código limpo e boas práticas de desenvolvimento.
