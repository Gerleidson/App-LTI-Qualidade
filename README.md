# Documentação do Aplicativo LTI Qualidade
![tela](https://github.com/Gerleidson/LTIQualidade/assets/88213553/5ec931a2-df4d-4d9a-997a-439f0fd30502)


## Introdução

O aplicativo oferece acesso rápido e fácil a informações de contato e links para as redes sociais da empresa. Além disso, o aplicativo fornece acesso a diferentes seções, como manutenção, instalação e outras.

## Requisitos do Sistema

- Dispositivo Android com versão mínima X.X.X (substituir com a versão mínima suportada).

## Instalação

1. Faça o download do arquivo APK do aplicativo.
2. Abra o arquivo APK no dispositivo Android.
3. Siga as instruções na tela para concluir a instalação.

## Funcionalidades Principais

### Tela Principal (MainActivity)

A tela principal do aplicativo apresenta os seguintes elementos:

- Botões de redes sociais:
  - Gmail: Ao clicar neste botão, o usuário pode enviar um e-mail para o endereço de e-mail da empresa.
  - WhatsApp: Ao clicar neste botão, o usuário é redirecionado para o WhatsApp da empresa para iniciar uma conversa.
  - Instagram: Ao clicar neste botão, o usuário é redirecionado para o perfil da empresa no Instagram.

- Botões de navegação:
  - Manutenção: Ao clicar neste botão, o usuário é levado para a seção "Manutenção" com informações detalhadas sobre os serviços de manutenção oferecidos pela empresa.
  - Instalação: Ao clicar neste botão, o usuário é levado para a seção "Instalação" com informações sobre os serviços de instalação oferecidos pela empresa.
  - Outros: Ao clicar neste botão, o usuário é levado para a seção "Outros" com informações sobre outros serviços e informações relevantes.

## Classes Principais

### MainActivity

A classe `MainActivity` é a atividade principal do aplicativo. Ela é responsável por configurar a interface do usuário e gerenciar os cliques nos botões.

#### Métodos Principais:

- `onCreate(Bundle savedInstanceState)`: Método chamado quando a atividade é criada. Ele configura o layout e associa os cliques dos botões.

- `manutencao(View view)`: Método associado ao botão "Manutenção" que inicia a atividade `Manutencao`.

- `instalacao(View view)`: Método associado ao botão "Instalação" que inicia a atividade `Instalacao`.

- `outros(View view)`: Método associado ao botão "Outros" que inicia a atividade `Outros`.

## Atividades Relacionadas

### Manutencao

A atividade `Manutencao` fornece informações detalhadas sobre os serviços de manutenção oferecidos pela empresa.

### Instalacao

A atividade `Instalacao` apresenta informações sobre os serviços de instalação oferecidos pela empresa.

### Outros

A atividade `Outros` fornece informações adicionais sobre serviços e informações relevantes fornecidas pela empresa.

## Considerações Finais

Esta documentação fornece uma visão geral do aplicativo "LTI Qualidade". Para obter informações mais detalhadas sobre implementações específicas, consulte o código fonte.

**Nota:** Este documento é uma representação genérica da documentação com base no código fornecido.
