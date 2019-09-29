public class CommandLineArgumentHandling {

    void processArguments(String arg) {

        String request[] = arg.split("=");
        String requestValue = request[1];

        if(request[0].equals("--personensuche")) { // argument is name
            formatString(requestValue);
            System.out.println(requestValue);
        }
        if(request[0].equals("--produktsuche")) { // argument is name
            formatString(requestValue);
        }
        if(request[0].equals("--produktnetzwerk")) { // argument is id
            formatString(requestValue);
            //cast String to int
        }
        if(request[0].equals("--firmennetzwerk")) { // argument is id
            formatString(requestValue);
            //cast String to int
        }
    }

    void formatString(String arg) {
        arg.replace("\"", "");
        arg.trim();
    }
}

