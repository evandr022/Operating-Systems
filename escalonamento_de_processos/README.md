# Trabalho Prático 1
## Escalonamento de Processos
O sistema operacional ELF possui implementado em seu núcleo seis (6) algoritmos de escalonamento de processo (FCFS, RR, SJF, SRTF preemptivo, Prioc e Priop). Desta forma, implemente um (mas podem ser seis) programa(s) (código(s)) – COM BASE NOS ALGORITMOS DISPONIBILIZADOS - que ao receber como entrada uma sequência de processos contendo PID, tempo de ingresso na fila de prontos, duração, prioridade e tipo (CPU bound, I/O bound ou ambos) os coloque em execução da melhor forma possível. Seu código precisa definir no início quais algoritmos poderão ser usados (no mínimo dois) e o quantum. Como saída, seu código deve informar a ordem de execução dos processos (PID) de acordo com o algoritmo escolhido com base do tipo do processo. Também deve ser fornecido o tempo médio de execução e de espera.

## IMPORTANTE:

- Sugestão: utilizar a linguagem C, java ou python.
- Prioridade 0 (zero) significa sem prioridade
- Quanto maior o valor, maior a prioridade
- Tipo de processo: CPU bound = 1; I/O bound = 2; ambos = 3; 
- Comentar o código fonte;