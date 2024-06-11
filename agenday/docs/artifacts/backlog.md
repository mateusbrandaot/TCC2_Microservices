## Backlog

Neste documento, são delineados os passos envolvidos na elaboração do <i>Product Backlog</i>, destacando cada fase do processo e documentando os artefatos gerados ao longo do percurso.

## Introdução ao Product Backlog

O **Product Backlog** é uma ferramenta fundamental na metodologia ágil de desenvolvimento de software, particularmente no framework Scrum. Ele consiste em uma lista ordenada de tudo que é necessário para o desenvolvimento e aperfeiçoamento de um produto de software. Esta lista é dinâmica e evolui ao longo do tempo, incorporando novas descobertas, ajustes e requisitos conforme o projeto progride. Cada item do *Product Backlog*, conhecido como *Backlog Item*, representa uma funcionalidade, melhoria ou correção que deve ser implementada pela equipe de desenvolvimento.

A priorização dos itens no *Product Backlog* é crucial para garantir que os recursos sejam alocados eficientemente e que as funcionalidades mais valiosas sejam desenvolvidas primeiro. Para facilitar essa priorização, utilizamos o método MoSCoW, uma técnica que categoriza os requisitos em quatro níveis de prioridade:

- **Must Have (Deve Ter)**: Requisitos essenciais sem os quais o sistema não pode ser considerado funcional ou completo. Estes são implementados como uma alta prioridade.
- **Should Have (Deveria Ter)**: Requisitos importantes que não são críticos para o lançamento inicial, mas que agregam valor significativo ao produto.
- **Could Have (Poderia Ter)**: Requisitos desejáveis que, embora úteis, têm um impacto menor na entrega final. Sua implementação é geralmente considerada apenas se houver tempo e recursos disponíveis após os itens mais críticos serem completados.
- **Won't Have (Não Terá)**: Requisitos que são reconhecidos como menos críticos ou menos viáveis neste ciclo de desenvolvimento. Eles não são planejados para implementação imediata mas podem ser reconsiderados no futuro.

Ao empregar o método MoSCoW, asseguramos uma abordagem estratégica para o desenvolvimento do produto, focando no que é mais urgente e valioso para os usuários finais e para os objetivos do negócio.

Este **Product Backlog** foi desenvolvido com o intuito de orientar as iterações de desenvolvimento e garantir que todas as partes interessadas tenham uma visão clara das prioridades e direcionamentos do projeto. A seguir, são apresentados os épicos e requisitos que compõem o backlog deste projeto, cada um com sua respectiva priorização conforme o método MoSCoW.

## Épico 01 - Autenticação e Autorização
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ001 | Como administrador, quero fazer login com usuário e senha para acessar de forma segura o sistema de gerenciamento de serviços. | Validar credenciais. Redirecionar para a área de administração após o login. | Must Have |
| REQ002 | Como administrador, quero redefinir minha senha através do meu e-mail para recuperar o acesso ao sistema. | Enviar e-mail com link de redefinição de senha. Permitir a criação de uma nova senha através do link. | Must Have |
| REQ003 | Como administrador, desejo ativar a autenticação de dois fatores para aumentar a segurança do acesso ao sistema. | Oferecer opção de ativação via interface do usuário. Suportar autenticação por SMS ou aplicativo autenticador. | Should Have |

## Épico 02 - Gerenciamento de Instituições
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ004 | Como administrador, quero cadastrar uma nova instituição com detalhes como nome, tipo, endereço e contato. | Formulário de cadastro com campos obrigatórios. Validação dos dados inseridos. | Must Have |
| REQ005 | Como administrador, quero atualizar ou remover instituições para manter as informações corretas e atualizadas. | Opções de editar e excluir para cada instituição cadastrada. Confirmação antes da exclusão de uma instituição. | Must Have |
| REQ006 | Como administrador, quero visualizar detalhes completos das instituições cadastradas para gerenciar eficientemente. | Lista detalhada com todas as instituições. Opções para visualizar mais detalhes de cada instituição. | Should Have |

## Épico 03 - Gerenciamento de Especialidades
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ007 | Como administrador, quero adicionar novas especialidades ao sistema para diversificar os serviços oferecidos. | Formulário para inserção de nova especialidade. Validação dos dados inseridos. | Must Have |
| REQ008 | Como administrador, quero editar ou remover especialidades para atualizar ou simplificar a oferta de serviços. | Opções de editar e excluir para cada especialidade. Confirmação antes da remoção de uma especialidade. | Should Have |

## Épico 04 - Gerenciamento de Colaboradores
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ009 | Como administrador, quero cadastrar colaboradores no sistema, incluindo informações como nome, CPF, e-mail, e seus horários disponíveis. | Formulário de cadastro com campos para nome, CPF, e-mail e horários disponíveis. Validação dos dados inseridos para garantir a precisão das informações. Possibilidade de definir múltiplos intervalos de disponibilidade por dia. | Must Have |
| REQ010 | Como administrador, quero atualizar ou remover colaboradores para manter a equipe atualizada com as necessidades do serviço. | Opções de editar e excluir para cada colaborador cadastrado. Capacidade de alterar informações pessoais e ajustar horários disponíveis. Confirmação antes da exclusão de um colaborador. | Must Have |
| REQ011 | Como administrador, quero gerenciar os horários de disponibilidade de cada colaborador para atualizar ou bloquear horários específicos conforme necessário. | Interface para visualizar e modificar os horários disponíveis de cada colaborador. Funcionalidade para adicionar, editar ou remover blocos de disponibilidade. Visualização clara de horários já agendados para evitar conflitos. | Should Have |

## Épico 05 - Gerenciamento de Agendamentos
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ012 | Como cliente, quero agendar serviços diretamente pelo sistema para facilitar a gestão do meu tempo. | Interface simplificada para seleção de serviço, data e horário. Confirmação imediata do agendamento. | Must Have |
| REQ013 | Como cliente, quero cancelar ou reagendar serviços para ajustar meus compromissos conforme necessário. | Opções de cancelamento e reagendamento acessíveis na área do cliente. Notificações enviadas ao prestador de serviço e ao cliente sobre alterações. | Should Have |

## Épico 06 - Catálogo de Serviços
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ014 | Como cliente, quero visualizar um catálogo de serviços para escolher o serviço que melhor atenda às minhas necessidades. | Listagem de todos os serviços disponíveis com detalhes como descrição, preço e duração. Funcionalidades de filtragem por categoria, localização e avaliação. | Must Have |
| REQ015 | Como administrador, quero adicionar serviços ao catálogo para disponibilizar mais opções aos clientes. | Formulário para inserção de novos serviços com campos para nome, descrição, preço e duração. Validação dos dados inseridos para garantir a precisão das informações. | Should Have |
| REQ016 | Como administrador, quero atualizar ou remover serviços do catálogo para manter as informações corretas e atuais. | Opções de editar e excluir para cada serviço listado. Confirmação antes da exclusão de um serviço. | Could Have |

## Épico 07 - Notificações
| ID     | Descrição | Critérios de Aceitação | Priorização |
|--------|-----------|------------------------|-------------|
| REQ017 | Como administrador, quero enviar notificações automáticas sobre atualizações de agendamento para clientes via e-mail. | Sistema capaz de enviar e-mails automaticamente quando um agendamento é criado, alterado ou cancelado. Personalização do conteúdo do e-mail conforme o tipo de notificação. | Should Have |
| REQ018 | Como administrador, quero enviar notificações automáticas sobre atualizações de agendamento para clientes via WhatsApp. | Integração com a API do WhatsApp para envio de mensagens. Mensagens automatizadas para notificar sobre a criação, alteração ou cancelamento de agendamentos. | Could Have |
| REQ019 | Como cliente, quero receber confirmações e lembretes de agendamentos via e-mail e WhatsApp para não esquecer dos meus compromissos. | Envio de confirmação imediata após o agendamento e lembretes um dia antes do compromisso. Opção para o cliente escolher o meio preferido de recebimento das notificações. | Should Have |

## Histórico de versões

### Histórico de Versões

| Versão | Data       | Descrição            | Autor(es)              | Revisor(es) |
|--------|------------|----------------------|------------------------|-------------|
| `1.0`  | 06/05/2024 | Criação do Documento | Todos                  | -           |


## Referências
 
 * COHN, M. Agile Estimating and Planning. Prentice Hall, 2005.
 * COHN, M. User Stories Applied. Addison-Wesley Professional, 2004.
 * SOMMERVILLE, I. Software Engineering. 9ª. ed. Pearson Education India, 2011.
