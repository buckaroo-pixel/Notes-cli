package com.example;

public class App {

    public static void main(String[] args) {
        String cmd = getArgValue(args, "--cmd");
        NotesStore store = new NotesStore();

        if (cmd == null || cmd.isBlank()) {
            printUsage();
            return;
        }

        switch (cmd) {
            case "add": {
                String text = getArgValue(args, "--text");
                if (text == null || text.isBlank()) {
                    System.out.println("Missing --text");
                    printUsage();
                    return;
                }
                store.add(text);
                break;
            }
            case "list": {
                store.list();
                break;
            }
            default:
                System.out.println("Unknown command: " + cmd);
                printUsage();
        }
    }

    // Поддерживает оба формата:
    // 1) --key=value
    // 2) --key value
    private static String getArgValue(String[] args, String key) {
        for (int i = 0; i < args.length; i++) {
            String a = args[i];

            // --key=value
            if (a.startsWith(key + "=")) {
                return a.substring((key + "=").length());
            }

            // --key value
            if (a.equals(key) && i + 1 < args.length) {
                return args[i + 1];
            }
        }
        return null;
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  --cmd=add  --text=\"...\"");
        System.out.println("  --cmd=list");
    }
}
