
# Biblioteca API — Spring Boot (Entrega de Atividade)

Projeto: API REST para gerenciar uma biblioteca virtual (cadastrar, listar, buscar, atualizar e remover livros).

Disciplina: Frameworks Web II — Laboratório Ciclo 2 (Unilavras)

Este repositório contém uma pequena API desenvolvida com Spring Boot, Spring Data JPA e H2 (banco em memória) para uso em atividades práticas da disciplina.

-----

## Estrutura do projeto

- `src/main/java` — código-fonte (model, repository, controller, aplicação)
- `src/main/resources/application.properties` — configurações (H2, JPA)
- `build.gradle` — configuração do Gradle (Java 21 toolchain)
- `gradle/wrapper/gradle-wrapper.properties` — configura o wrapper Gradle (IntelliJ/Gradle fará o download automático)

## Requisitos

- Java 21 (o `build.gradle` está configurado para usar toolchain Java 21)
- IntelliJ IDEA ou Gradle instalado (recomendo IntelliJ para facilitar)
- Para executar via terminal: Gradle ou `gradlew` (wrapper). Se o wrapper não existir localmente, o IntelliJ normalmente baixa a distribuição automaticamente usando `gradle-wrapper.properties`.

## Como executar (passos simples)

Opção A — IntelliJ (recomendado para entrega)
1. Abra o IntelliJ → File → Open → escolha a pasta raiz do projeto.
2. Importe como projeto Gradle (aceite as opções padrão). Em *Gradle JVM* selecione um JDK 21.
3. Aguarde a importação e o download das dependências.
4. Abra `src/main/java/com/example/demo/DemoApplication.java` e clique no triângulo verde ao lado do `main` → Run 'DemoApplication'.

Opção B — Terminal (PowerShell) com Gradle instalado
1. Na raiz do projeto:

```powershell
# (se não tiver wrapper e tiver gradle instalado)
gradle wrapper
.\gradlew.bat test
.\gradlew.bat bootRun
```

2. A aplicação roda em `http://localhost:8080`.

Opção C — Se não quiser instalar Gradle
- Use o IntelliJ (passo A) — ele baixa o Gradle automaticamente.

-----

## Endpoints (API)

Base: `http://localhost:8080/api/livros`

- POST `/api/livros`
	- Cria um livro. Retorna `201 Created` e cabeçalho `Location` com o recurso criado.
	- Exemplo body (JSON):
		```json
		{
			"titulo": "Introdução ao Spring",
			"autor": "Fulano",
			"isbn": "978-1234567897",
			"anoPublicacao": 2023
		}
		```

- GET `/api/livros` — lista todos os livros
- GET `/api/livros/{id}` — busca por id (200 ou 404)
- PUT `/api/livros/{id}` — atualiza (usa mesmo JSON do POST)
- DELETE `/api/livros/{id}` — remove (204 No Content)

Exemplo rápido com `curl` (Windows PowerShell):
```powershell
# Criar
curl -H "Content-Type: application/json" -d '{"titulo":"Livro A","autor":"Autor A","isbn":"111","anoPublicacao":2020}' http://localhost:8080/api/livros -Method POST

# Listar
curl http://localhost:8080/api/livros
```

## H2 Console

- URL: `http://localhost:8080/h2-ui`
- JDBC URL: `jdbc:h2:mem:biblioteca`
- User: `sa` (senha em branco)

-----

## Testes

- Um teste de contexto básico está em `src/test/java/com/example/demo/DemoApplicationTests.java`.
- Execute via IntelliJ (Run test) ou `.\gradlew.bat test`.

-----

## Entrega (o que enviar)

1. Grave um vídeo demonstrando:
	 - Código aberto (mostrar `Livro`, `LivroController`, `application.properties`).
	 - Execução da aplicação (`bootRun` via IntelliJ ou terminal).
	 - Uso do Postman (ou `curl`) para: criar, listar, buscar, atualizar e deletar um livro.
	 - Console H2 mostrando os registros persistidos.

2. Compacte a pasta `src` e envie junto com o link do vídeo.
	 - No PowerShell (na raiz do projeto):
		 ```powershell
		 Compress-Archive -Path .\src\ -DestinationPath .\src.zip -Force
		 ```

3. Suba o vídeo para o Google Drive e gere um link de compartilhamento (ou outra nuvem pública) e cole o link no formulário da atividade.

Checklist (o que deve constar no envio):
- `src.zip` contendo a pasta `src` do projeto
- Link público do vídeo demonstrando todas as operações acima
- Código legível e funcionando (endpoints CRUD testados)

-----

## Dicas e observações

- Validação: os campos do `Livro` têm validações simples (`@NotBlank`, `@NotNull`). Requisições inválidas irão retornar `400 Bad Request` com mensagens padrão.
- CORS: o controller permite testes locais/externos (`@CrossOrigin(*)`).
- Se a importação Gradle falhar no IntelliJ, selecione *Use gradle wrapper* e aguarde o download do Gradle; caso persista, instale o Gradle localmente.

-----
