# GoodVideo Upload Application
Essa aplicação tem como objetivo receber, por endpoint, um vídeo em formato mp4, limitado ao tamanho pré-definido, realizar o upload do vídeo em um repositório externo que garantirá a segurança de acesso.

## Features
Aplicação responsável pelo upload do vídeo para processamento assíncrono.

### Fluxo geral da aplicação
- Validação do formato e tamanho limitado, por parâmetro no arquivo yaml, a 10Mb iniciais;
- Ao receber o arquivo é validado o token do usuário;
- Arquivo de vídeo é salvo em um bucket respeitando a hierarquia de pastas: <bucketName>/<idUsuario>/<idProcessamento>.
Dessa forma garantimos que cada usuário contenha sua pasta de arquivos de processamento, organizando e criando uma segurança para que nenhum vídeo seja misturado e acessado indevidamente;
- Publica-se uma mensagem para um tópico kafka para a app que realizará o processamento do vídeo obtendo as imagens;
Garantindo  que a aplicação executará de forma assíncrona e paralela a extração das imagens;
- A app contém um listener que será notificado quando a extração das imagens e upload do zip finalizar, atualizando as informações do diretório onde o arquivo compactado (zip) ficou armazenado (no bucket de referência, nas pasta do processamento);
- Caso ocorra algum erro, ouvimos o tópico que conterá o status do processamento e então, notificamos o usuário por email de que ocorreu um erro.
- A app contém um endpoint de listagem de processamento;
- Contém um job que remove o zip após 24h reduzindo o custo de armazenagem;

## Tecnologias
 - Java
 - Kafka
 - Postegres
 - Amazon S3
 - Amazon SES

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=goodvideo-postech-org_goodvideo-upload&metric=coverage)](https://sonarcloud.io/summary/new_code?id=goodvideo-postech-org_goodvideo-upload)