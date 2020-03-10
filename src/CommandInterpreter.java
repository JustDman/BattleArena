import java.util.Scanner;

class CommandInterpreter {
    Scanner scan;

    public CommandInterpreter() {
        this.scan = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("MENU");
        System.out.println("1) SHOW");
        System.out.println("2) CALC");
        System.out.println("3) EXIT");
    }

    public void start() {
        String s = "-";
        while (!s.equals("3")) {
            showMenu();
            s = scan.nextLine();
            switch (s) {
                case "1":
                    this.show();
                    break;
                case "2":
                    this.calc();
                    break;
                case "3":
                    break;
                default:
                    this.error();
                    break;
            }
        }
        scan.close();
    }

    public void show() {
        System.out.println("SHOW");
    }

    public void calc() {
        System.out.println("CALC 1+1 = 2");
    }

    public void error() {
        System.out.println("ERROR!");
    }

}