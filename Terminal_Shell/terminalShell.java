import java.io.*;
import java.util.*;

public class terminalShell {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            String comando = "";
            String[] comandos01;
            String[] comandos02;
            String[] comandos03;
            String[] comandos04;
            String[] comandos05;
            String[] comandos06;
            String[] comandos07;
            String[] comandos08;
            String[] comandos09;
            String[] comandos10;
      
            while (!comando.equals("exit")) {
                System.out.print("user@shell:~$ ");
                comando = sc.nextLine();
                comandos01 = comando.split(" ");
                comandos02 = comando.split(" ");
                comandos03 = comando.split(" ");
                comandos04 = comando.split(" ");
                comandos05 = comando.split(" ");
                comandos06 = comando.split(" ");
                comandos07 = comando.split(" ");
                comandos08 = comando.split(" ");
                comandos09 = comando.split(" ");
                comandos10 = comando.split(" ");
                
                if (comandos01[0].equals("cd")) {
                    if (comandos01.length == 1) {
                        System.out.println("cd: argumento faltando");
                    } else if (comandos01.length > 2) {
                        System.out.println("cd: muitos argumentos");
                    } else {
                        File dir = new File(comandos01[1]);
                        if (dir.exists()) {
                            System.setProperty("user.dir", comandos01[1]);
                        } else {
                            System.out.println("cd: " + comandos01[1] + ": Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos02[0].equals("pwd")) {
                    System.out.println(System.getProperty("user.dir"));
                } else if (comandos03[0].equals("ls")) {
                    File dir = new File(System.getProperty("user.dir"));
                    File[] filesList = dir.listFiles();
                    for (File file : filesList) {
                        if (file.isFile()) {
                            System.out.println(file.getName());
                        }
                    }
                } else if (comandos04[0].equals("mkdir")) {
                    if (comandos04.length == 1) {
                        System.out.println("mkdir: argumento faltando");
                    } else if (comandos04.length > 2) {
                        System.out.println("mkdir: muitos argumentos");
                    } else {
                        File dir = new File(comandos04[1]);
                        if (!dir.exists()) {
                            dir.mkdir();
                        } else {
                            System.out.println("mkdir: não foi possível criar o diretório '" + comandos04[1] + "': Arquivo já existe");
                        }
                    }
                } else if (comandos05[0].equals("rmdir")) {
                    if (comandos05.length == 1) {
                        System.out.println("rmdir: argumento faltando");
                    } else if (comandos05.length > 2) {
                        System.out.println("rmdir: muitos argumentos");
                    } else {
                        File dir = new File(comandos05[1]);
                        if (dir.exists()) {
                            dir.delete();
                        } else {
                            System.out.println("rmdir: não foi possível remover o diretório '" + comandos05[1] + "': Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos06[0].equals("cp")) {
                    if (comandos06.length == 1) {
                        System.out.println("cp: argumento faltando");
                    } else if (comandos06.length > 3) {
                        System.out.println("cp: muitos argumentos");
                    } else {
                        File dir = new File(comandos06[1]);
                        File dir2 = new File(comandos06[2]);
                        if (dir.exists()) {
                            if (!dir2.exists()) {
                                dir2.createNewFile();
                            }
                            FileReader in = new FileReader(dir);
                            FileWriter out = new FileWriter(dir2);
                            int c;
                            while ((c = in.read()) != -1) {
                                out.write(c);
                            }
                            in.close();
                            out.close();
                        } else {
                            System.out.println("cp: não foi possível copiar '" + comandos06[1] + "': Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos07[0].equals("mv")) {
                    if (comandos07.length == 1) {
                        System.out.println("mv: argumento faltando");
                    } else if (comandos07.length > 3) {
                        System.out.println("mv: muitos argumentos");
                    } else {
                        File dir = new File(comandos07[1]);
                        File dir2 = new File(comandos07[2]);
                        if (dir.exists()) {
                            if (!dir2.exists()) {
                                dir2.createNewFile();
                            }
                            FileReader in = new FileReader(dir);
                            FileWriter out = new FileWriter(dir2);
                            int c;
                            while ((c = in.read()) != -1) {
                                out.write(c);
                            }
                            in.close();
                            out.close();
                            dir.delete();
                        } else {
                            System.out.println("mv: não foi possível mover '" + comandos07[1] + "': Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos08[0].equals("rm")) {
                    if (comandos08.length == 1) {
                        System.out.println("rm: argumento faltando");
                    } else if (comandos08.length > 2) {
                        System.out.println("rm: muitos argumentos");
                    } else {
                        File dir = new File(comandos08[1]);
                        if (dir.exists()) {
                            dir.delete();
                        } else {
                            System.out.println("rm: não foi possível remover '" + comandos08[1] + "': Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos09[0].equals("cat")) {
                    if (comandos09.length == 1) {
                        System.out.println("cat: argumento faltando");
                    } else if (comandos09.length > 2) {
                        System.out.println("cat: muitos argumentos");
                    } else {
                        File dir = new File(comandos09[1]);
                        if (dir.exists()) {
                            FileReader in = new FileReader(dir);
                            int c;
                            while ((c = in.read()) != -1) {
                                System.out.print((char) c);
                            }
                            in.close();
                        } else {
                            System.out.println("cat: não foi possível ler '" + comandos09[1] + "': Arquivo ou diretório não encontrado");
                        }
                    }
                } else if (comandos10[0].equals("exit")) {
                    System.out.println("Saindo do shell...");
                } else {
                    System.out.println("Comando não encontrado");
                }
            }
        }
    }
}