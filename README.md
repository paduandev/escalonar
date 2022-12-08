# Escalonar v 1.0

## Introdu??o

Este software foi escrito para auxiliar o ensino de algoritmos de escalonamento de processos em sistemas operacionais.
Como resultado de sua execu??o é exibido um gr?fico de Gantt representando a escolha e execu??o de cada processo no escalonamento, e tamb?m são calculados os tempos de espera e total de execu??o de cada processo, e os tempos m?dios para o conjunto de processos fornecido.

## Considera??es Gerais

- O ambiente considerado ? mono-processado (um ?nico n?cleo de processamento).
- Todos os processos são orientados (limitados) a processador, ou seja, não fazem I/O.
- A trova de contexto tem dura??o nula (zero).
- Não são exibidas unidades de medida para os tempos, mas são considerados valores inteiros representando milissegundos.
- A escolha dos processos no escalonamento segue a seguinte ordem:
  1.  pol?tica do algoritmo (por exemplo, dura??o no caso do SJF)
  2.  ordem de chegada
  3.  nome do processo (como ?ltimo crit?rio para desempate)

## Requisitos

É necess?rio no m?nimo Java 8 instalado na m?quina para a execu??o correta de todas as funcionalidades do aplicativo.

## Tela Inicial

![Tela Inicial](/img/telaprin.png)

Na tela inicial do aplicativo ? poss?vel acionar os seguintes menus:

- **Escalonador**: permite escolher qual o algoritmo de escalonamento ser? executado. Uma marca ser? mostrada no menu indicando qual o algoritmo atualmente selecionado. O default é FIFO;
- **Processos**: permite inserir os processos que ser?o utilizados no escalonamento ou grav?-los em arquivo para uso posterior;
- **Op??es**: permite alterar op??es de interface do aplicativo
- **Extra**: permite exportar os resultados do escalonamento para um arquivo no formato pdf;
- **Ajuda**: informa??es b?sicas sobre o funcionamento do aplicativo.

Tamb?m na tela principal é poss?vel acionar o bot?o _calcular_ que executa o escalonamento de acordo com os par?metros fornecidos. Como exemplo de resultado de execu??o, observe a tela a seguir, onde são escalonados sete processos utilizando o algoritmo FCFS.
O quadro à esquerda exibe os dados de entrada, o quadro à direita exibe os dados resultantes do escalonamento. O gr?fico exibe a ordem em que os processos foram escalonados. As cores são aleat?rias e podem ser alteradas clicando novamente no bot?o _calcular_. Com os mesmos dados de entrada pode-se escolher outro algoritmo no menu _Escalonar_ e clicar no bot?o _calcular_ para obter o resultado do escalonamento pelo algoritmo escolhido.

![Tela do escalonamento](/img/telaprin2.png)

# Algoritmos implementados

Os seguintes algoritmos foram implementados:

- FCFS ( _First Come-First Served_ : O primeiro a chegar é o primeiro a ser atendido ):
  Algoritmo não preemptivo. Escalona os processos por ordem de chegada.
- SJF ( _Shortest Job First_ : Menor processo primeiro )
  Algoritmo não preemptivo. Escalona os processos (considerando os processos que j? est?o na fila de prontos) de acordo com o tempo de dura??o. Os processos de menor dura??o são escalonados primeiro.
- SRT ( _Shortest Remaining Time_: Menor tempo restante primeiro )  
  É a vers?o preemptiva do SJF. Escalona os processos (considerando os processos que já est?o na fila de prontos) de acordo com o tempo de dura??o restante. Os processos de menor dura??o restante são escalonados primeiro. Por ser preemptivo, um processo pode ser interrompido caso durante a sua execu??o chegue um outro processo cuja dura??o seja menor do que a dura??o restante deste processo em execu??o. Dura??o restante significa o tempo que falta para o processo terminar sua execu??o.
- PRIORIDADE NP:
  Algoritmo não preemptivo. Escalona os processos (considerando os processos que já est?o na fila de prontos) de acordo com o seu valor de prioridade. Os processos com maior valor de prioridade são escalonados primeiro.
- PRIORIDADE P:
  Algoritmo preemptivo. Escalona os processos (considerando os processos que já est?o na fila de prontos) de acordo com o seu valor de prioridade. Os processos com maior valor de prioridade são escalonados primeiro. Por ser preemptivo, um processo pode ser interrompido caso durante a sua execu??o chegue um outro processo cuja prioridade seja maior do que a prioridade deste processo em execu??o.
- ROUND ROBIN:
  Algoritmo preemptivo. Escalona os processos (considerando os processos que já est?o na fila de prontos) de acordo a ordem de entrada na fila. A cada _Quantum_ de tempo há uma interrup??o do processo em execu??o para que o pr?ximo processo da fila seja executado.

# Entrada e Sa?da de Processos

- _Lendo de um arquivo_: carrega os dados dos processos de entrada a partir do arquivo fornecido como entrada. É feita uma valida??o para verificar se o arquivo est? no formato esperado e, em caso de formato ou dados inv?lidos, o arquivo não é carregado.
- _Gravando em um arquivo_: grava em arquivo os dados dos processos de entrada. Os dados de sa?da, calculados no escalonamento, não são gravados.
- _Digitando os dados_: abre a tela para digita??o dos processos a serem usados no escalonamento.

Os arquivos utilizados como entrada e sa?da são do tipo texto, com a extens?o ._prc_.
Os dados de cada um dos processos deve ser colocado em uma linha separada. Cada uma destas linhas deve conter as seguintes informa??es (e? ordem) separadas por ponto-e-v?rgula:
_chegada ; dura??o ; prioridade_

# Digita??o de Processos

![Tela de processos](/img/processos.png)

Nesta tela é poss?vel digitar os processos que ser?o utilizados como entrada do escalonamento. Caso nenhum processo tenha sido digitado ainda, somente uma linha ser? apresentada com o r?tulo _P1_ sendo apresentado no nome do processo. Os r?tulos são gerados automaticamente não sendo permitido sua edi??o.
Se já houverem dados de processos digitados anteriormente, todos eles ser?o apresentados para edi??o, como ilustrado a seguir.

![Tela de processos](/img/processos2.png)

Os seguintes bot?es são apresentados na tela de edi??o:

- **Inserir**: acrescenta uma nova linha para entrada de um novo processo;
- **Remover**: remove a linha onde o cursor de edi??o estiver posicionado;
- **Cancelar**: cancela todo o efeito de edi??o atual voltando à tela inicial sem nenhuma modifica??o nos processos;
- **OK**: confirma a edi??o feita no conjunto de processos. Os dados são validados antes de serem atualizados na tela principal.

Os r?tulos dos processos ser?o refeitos (renumerados a partir de 1) caso tenha havido remo??o de algum processo.

# Op??es

- Alterando o idioma: Os idiomas dispon?veis s?o Portugu?s e Ingl?s. O idioma padr?o ao abrir o aplicativo ? determinado pelo Sistema Operacional em uso.
- Alterando o tipo de gr?fico: Existem tr?s diferentes modelos de desenho aplicados para o gr?fico que podem ser escolhidos nesta op??o de menu.

# Gerar sa?da em formato pdf

Por meio desta op??o ? poss?vel gerar um arquivo em formato pdf contendo os dados dos processos de entrada, o gr?fico de Gantt resultante, e dos dados calculados para o conjunto de processos durante o escalonamento.
**OBS**: Depend?ncia: _itextpdf-5.5.13_
