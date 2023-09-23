import java.io.*;
import java.util.*;
import java.nio.file.Files;

class Dados {
    String id;
    int ingresso;
    int duracao;
    int prio;
    int tipo;
    int aux_duracao;

    public Dados() {
    }

    public Dados(String id, int ingresso, int duracao, int prio, int tipo) {
        this.id = id;
        this.ingresso = ingresso;
        this.duracao = duracao;
        this.prio = prio;
        this.tipo = tipo;
        this.aux_duracao = duracao;
    }

    public String toString() {
        return ("" + id + " " + ingresso + " " + duracao + " " + prio + " " + tipo);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        try (Scanner scan = new Scanner(System.in)) {
            System.out.printf("\nInsira o nome do arquivo: ");
            String path = scan.nextLine();

            if (!Files.exists(new File(path).toPath())) {
                System.out.println("Arquivo nao encontrado");
                return;
            }

            List<Dados> listaDados = new ArrayList<>();

            try (FileReader fileReader = new FileReader(path)){
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linha;

                while ((linha = bufferedReader.readLine()) != null) {
                    if (!linha.isEmpty()) {
                        String[] dados = linha.split(" ");
                        if (dados.length >= 5) {
                            Dados dado = new Dados  (dados[0], 
                                    Integer.parseInt(dados[1]), 
                                    Integer.parseInt(dados[2]),
                                    Integer.parseInt(dados[3]), 
                                    Integer.parseInt(dados[4]));
                            listaDados.add(dado);
                        } else {
                            System.out.println("Linha com dados incompletos: " + linha);
                        }
                    }
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }

			Dados dado = listaDados.get(0);

            if (dado == null) {
                System.out.println("Lista de dados vazia.");
            } else {
                int tipo = dado.tipo;

                switch (tipo) {
                    case 1:
                        System.out.println("\nCPU bound\n");
                        System.out.println("1. RR\n2. SRTF\n3. PrioP");
                        int alg = scan.nextInt();
                        System.out.printf("Insira o Quantum: ");
                        int quantum = scan.nextInt();
                
                        switch (alg) {
                            case 1:
                                Round_Robin(listaDados, quantum);
                                break;
                            case 2:
                                srtf(listaDados, quantum);
                                break;
                            case 3:
                                prioP(listaDados, quantum);
                                break;
                            default:
                                System.out.println("Algoritmo não encontrado.");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("\nI/O bound\n");
                        System.out.println("1. FCFS\n2. SJF\n3. PrioC");
                        alg = scan.nextInt();
                        switch (alg) {
                            case 1:
                                fcfs(listaDados);
                                break;
                            case 2:
                                sjf(listaDados);
                                break;
                            case 3:
                                prioC(listaDados);
                                break;
                            default:
                                System.out.println("Algoritmo não encontrado.");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("\nAmbos\n");
                        System.out.println("1. FCFS\n2. SJF\n3. PrioC\n4. RR\n5. SRTF\n6. PrioP");
                        alg = scan.nextInt();
                            switch (alg) {
                                case 1:
                                    fcfs(listaDados);
                                    break;
                                case 2:
                                    sjf(listaDados);
                                    break;
                                case 3:
                                    prioC(listaDados);
                                    break;
                                case 4:
                                    System.out.printf("Insira o Quantum: ");
                                    int quantum2 = scan.nextInt();
                                    Round_Robin(listaDados, quantum2);
                                    break;
                                case 5:
                                    System.out.printf("Insira o Quantum: ");
                                    int quantum3 = scan.nextInt();
                                    srtf(listaDados, quantum3);
                                    break;
                                case 6:
                                    System.out.printf("Insira o Quantum: ");
                                    int quantum1 = scan.nextInt();
                                    prioP(listaDados, quantum1);
                                    break;
                                default:
                                    System.out.println("Algoritmo não encontrado.");
                                    break;
                            }
                        break;
                    default:
                        System.out.println("Tipo não encontrado.");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Algoritmos FCFS (Ingresso)
    public static void fcfs(List<Dados> listaDados) {
        System.out.println("\nAlgoritmo FCFS:");

        int tempo = 0;
        int tempo_execucao = 0;
        int tempo_espera = 0;

        double tamanho = listaDados.size();
		double total_execucao = 0;
		double total_espera = 0;

        for (Dados dado : listaDados) {
            if (dado.ingresso > tempo) {
                tempo = dado.ingresso + dado.duracao;
            } else {
                tempo += dado.duracao;
            }

            System.out.printf("%s ", dado.id);

            tempo_execucao += tempo;
            tempo_espera += tempo - dado.ingresso - dado.duracao;
        }
		total_execucao = tempo_execucao / tamanho;
		total_espera = tempo_espera / tamanho;

        System.out.printf("\nTempo de execucao medio: %.2f", total_execucao);
        System.out.printf("\nTempo de espera medio: %.2f", total_espera);
    }

    // Algoritmos SJF (Duração)
    public static void sjf(List<Dados> listaDados) {
        System.out.println("\nAlgoritmo SJF:");

        double tempo = 0;
		double tempo_execucao = 0;
		double tempo_espera = 0;

		double tamanho = listaDados.size();

        while (listaDados.size() > 0) {
            Dados dadoMenor = listaDados.get(0);
            for (Dados dado : listaDados) {
                if (dado.duracao < dadoMenor.duracao) {
                    dadoMenor = dado;
                } 
            }

            if (dadoMenor.ingresso > tempo) {
                tempo = dadoMenor.ingresso + dadoMenor.duracao;
            } else {
                tempo += dadoMenor.duracao;
            }
            listaDados.remove(dadoMenor);

            System.out.printf("%s ", dadoMenor.id);
			
			tempo_execucao += tempo;
			tempo_espera += tempo - dadoMenor.ingresso - dadoMenor.duracao;
        }

		System.out.printf("\nTempo de execucao medio: %.2f", tempo_execucao / tamanho);
		System.out.printf("\nTempo de espera medio: %.2f", tempo_espera / tamanho);

    }

    // Algoritmos PrioC (Prioridade)
	public static void prioC(List<Dados> listaDados) {
		System.out.println("\nAlgoritmo PrioC:");

        double tempo = 0;
		double tempo_execucao = 0;
		double tempo_espera = 0;

		double tamanho = listaDados.size();

        while (listaDados.size() > 0) {
            Dados dadoMaior = listaDados.get(0);
            for (Dados dado : listaDados) {
                if (dado.prio > dadoMaior.prio && dado.ingresso <= tempo) {
                    dadoMaior = dado;
                } 	
            }

            if (dadoMaior.ingresso > tempo) {
                tempo = dadoMaior.ingresso + dadoMaior.duracao;
            } else {
                tempo += dadoMaior.duracao;
            }
            listaDados.remove(dadoMaior);

            System.out.printf("%s ", dadoMaior.id);
			
			tempo_execucao += tempo;
			tempo_espera += tempo - dadoMaior.ingresso - dadoMaior.duracao;
        }

		System.out.printf("\nTempo de execucao medio: %.2f", tempo_execucao / tamanho);
		System.out.printf("\nTempo de espera medio: %.2f", tempo_espera / tamanho);


	}

    // Algoritmos Round-Robin (Ingresso - preempção)
    public static void Round_Robin(List<Dados> listaDados, int quantum) {
        System.out.println("\nAlgoritmo Round-Robin:");

        int tempo = 0;
		double tempo_execucao = 0;
		double tempo_espera = 0;
		double tamanho = listaDados.size();
        List<Dados> dados = new ArrayList<>();

        while (listaDados.size() > 0){
            for (Dados dado : listaDados) {
                if (dado.ingresso <= tempo) {
                    dados.add(dado);
                } 	
            }
            for (Dados dado : dados){
                tempo += 1;
                if (dado.duracao > quantum) {
                    System.out.printf("%s ", dado.id);
                    //System.out.println(tempo);
                    dado.duracao -= quantum;
                } else {
                    System.out.printf("%s ", dado.id);
                    //System.out.println(tempo + " f");
                    tempo_execucao += tempo - dado.ingresso;
                    tempo_espera += tempo - dado.aux_duracao - dado.ingresso;
                    listaDados.remove(dado);
                }
            }
            if (dados.isEmpty()){
                tempo += 1;
            }
            dados.clear();
        }
        System.out.printf("\nTempo de execucao medio: %.2f", tempo_execucao / tamanho);
        System.out.printf("\nTempo de espera medio: %.2f", tempo_espera / tamanho);
    }

    // Algoritmos SRTF (Duração - preempção)
	public static void srtf(List<Dados> listaDados, int quantum) {
		System.out.println("\nAlgoritmo SRTF:");
        int tempo = 0;
		double tempo_execucao = 0;
		double tempo_espera = 0;
		double tamanho = listaDados.size();
        
        while (listaDados.size() > 0) {
            for (int a = 0; a < listaDados.size(); a++) {
                Dados dadoMenor = listaDados.get(0);
                for (Dados dado : listaDados) {
                    if (dado.duracao < dadoMenor.duracao && dado.ingresso <= tempo) {
                        dadoMenor = dado;
                    } 	
                }
                tempo += quantum;
                if (dadoMenor.duracao > quantum) {
                    System.out.printf("%s ", dadoMenor.id);
                    dadoMenor.duracao -= quantum;
                } else {
                    System.out.printf("%s ", dadoMenor.id);
                    tempo_execucao += tempo;
                    tempo_espera += tempo - dadoMenor.aux_duracao - dadoMenor.ingresso;
                    listaDados.remove(dadoMenor);
                    a -= 1;
                }
                
            }
        }
        System.out.printf("\nTempo de execucao medio: %.2f", tempo_execucao / tamanho);
        System.out.printf("\nTempo de espera medio: %.2f", tempo_espera / tamanho);
	}

    // Algoritmos PrioP (Prioridade - preempção)
	public static void prioP(List<Dados> listaDados, int quantum) {
		System.out.println("\nAlgoritmo PrioP:");

        int tempo = 0;
		double tempo_execucao = 0;
		double tempo_espera = 0;
		double tamanho = listaDados.size();
        
        while (listaDados.size() > 0) {
            for (int a = 0; a < listaDados.size(); a++) {
                Dados dadoMenor = listaDados.get(0);
                for (Dados dado : listaDados) {
                    if (dado.prio > dadoMenor.prio && dado.ingresso <= tempo) {
                        dadoMenor = dado;
                    } 	
                }
                tempo += quantum;
                if (dadoMenor.duracao > quantum) {
                    System.out.printf("%s ", dadoMenor.id);
                    dadoMenor.duracao -= quantum;
                } else {
                    System.out.printf("%s ", dadoMenor.id);
                    tempo_execucao += tempo;
                    tempo_espera += tempo - dadoMenor.aux_duracao - dadoMenor.ingresso;
                    listaDados.remove(dadoMenor);
                    a -= 1;
                }
                
            }
        }
        System.out.printf("\nTempo de execucao medio: %.2f", tempo_execucao / tamanho);
        System.out.printf("\nTempo de espera medio: %.2f", tempo_espera / tamanho);
    }
}
