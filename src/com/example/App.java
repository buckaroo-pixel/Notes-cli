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
            case "count": {
                store.count();
                break;
            }
            case "rm": {
                String idStr = getArgValue(args, "--id");
                if (idStr == null) {
                    System.out.println("Missing --id");
                    printUsage();
                    return;
                }
                try {
                    int id = Integer.parseInt(idStr);
                    store.remove(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid id");
                }
                break;
            }
            default:
                System.out.println("Unknown command: " + cmd);
                printUsage();
        }
    }

    // поддерживает:
    // --key=value
    // --key value
    private static String getArgValue(String[] args, String key) {
        for (int i = 0; i < args.length; i++) {
            String a = args[i];

            if (a.startsWith(key + "=")) {
                return a.substring((key + "=").length());
            }

            if (a.equals(key) && i + 1 < args.length) {
                return args[i + 1];
            }
        }
        return null;
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  --cmd=add   --text=\"...\"");
        System.out.println("  --cmd=list");
        System.out.println("  --cmd=count");
        System.out.println("  --cmd=rm    --id=N");
    }
}
