# Projeto de Teste com TestNG no Serverest

Este é um projeto de teste automatizado utilizando **TestNG** para realizar testes de API no **Serverest**. O objetivo deste projeto é garantir que os endpoints da API estejam funcionando corretamente e atender aos requisitos especificados.

## Pré-requisitos

Antes de começar, verifique se você tem os seguintes pré-requisitos instalados em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 11 ou superior)
- [Maven](https://maven.apache.org/download.cgi) (para gerenciar dependências)
- [TestNG](https://testng.org/doc/index.html) (geralmente incluído nas dependências do Maven)

## Configuração

1. Clone o repositório:

   ```bash
   git clone https://github.com/seuusuario/seurepositorio.git
   cd seurepositorio
   ```

2. Navegue até a pasta do projeto:

   ```bash
   cd seuprojeto
   ```

3. Instale as dependências do Maven:

   ```bash
   mvn clean install
   ```
4. Adicione o arquivo do properties no seguinte comando: src > main > resources > application-config.properties

```

spring.application.name=serverest-testng

USER_EMAIL=fulanotest@qa.com.br
USER_PSW=teste
USER_ID=0ddv8VNsyYk4T8E2

```

## Executando os Testes

Para executar os testes, você pode executar com o botão direito o diretório de testes ou rodar o testng.xml localizado na pasta ```test > resources```:


## Comparativo entre TestNG e JUnit

Documento com as diferenças, benefícios e utilização do **TestNG** e **JUnit**, consulte o seguinte documento:

- [Comparativo entre TestNG e JUnit](https://docs.google.com/document/d/1Cl4r-sBGgpRJSLHfaDl7NHwZKzx5_6YxeEp8QbDLIfM/edit?usp=sharing)

## Certificados dos cursos
### JUnit:
![img.png](img.png)

### TestNG:
![img_1.png](img_1.png)

---
## Autor

<a href="https://github.com/rafael-isidro">
    <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/118776145?v=4" width="100px;" alt="Foto de perfil - Rafael Isidro"/>
    <br />
    <sub><b>Rafael Santos Isidro</b></sub>
</a> 
<br />
