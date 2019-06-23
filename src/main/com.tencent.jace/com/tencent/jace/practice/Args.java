package com.tencent.jace.practice;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Args {

    Map<String, Class<?>> schemaMaps = new HashMap<>();

    Map<String, String> commandMaps = new HashMap<>();

    Map<String, Object> defaultArgMaps = new HashMap<>();

    public Args(String schema, String command) {
        //resolve schema
        parseSchema(schema);
        //parse command
        parseCommandLine(command);

    }

    private void parseSchema(String schema) {
        List<String> eneties = Arrays.asList(schema.split(","));
        eneties.forEach(x -> {
            String[] entry = x.split(":");
            schemaMaps.put(entry[0], getKeyClass(entry[1]));
            defaultArgMaps.put(entry[0], getDefaultArg(entry[0]));
        });
    }

    public Object getDefaultArg(String schema) {

        if ("l".equalsIgnoreCase(schema)) {
            return false;
        }
        if ("f".equalsIgnoreCase(schema)) {
            return ".";
        }

        if ("s".equalsIgnoreCase(schema)) {
            return null;
        }

        if ("d".equalsIgnoreCase(schema)) {
            return 0;
        }

        return new UnsupportError("Unspport Error");
    }


    public void parseCommandLine(String command) {
        List<String> commandEneties = Arrays.asList(command.split(" "));

        if (commandEneties.size() == 1) {
            commandMaps.put(standardStr(commandEneties.get(0)), null);
            return;
        }

        for (int i = 0; i < commandEneties.size() - 1; i ++) {

            String option = commandEneties.get(i);
            if (isAvaliableSchemaKey(option)) {
                StringBuilder stringBuilder = new StringBuilder();
                int j = i + 1;
                while(j < commandEneties.size() && !isAvaliableSchemaKey(commandEneties.get(j))) {
                    stringBuilder.append(commandEneties.get(j));
                    j ++;
                }

                String arg = stringBuilder.toString();
                if (isAvaliableSchemaKey(arg)) {
                    arg = null;
                } else {
                    i = j - 1;
                }

                commandMaps.put(standardStr(option), standardStr(arg));
            }
        }
    }

    private String standardStr(String option) {
        if (option == null) {
            return null;
        }
        return option.replace("-", "").toLowerCase().trim();
    }

    public boolean isAvaliableSchemaKey(String option) {
        if (!option.startsWith("-")) {
            return false;
        }

        String optionKey = option.replace("-", "");
        if (schemaMaps.containsKey(optionKey)) {
            return true;
        }
        return false;
    }

    public Class<?> getKeyClass(String s) {
        if ("bool".equalsIgnoreCase(s)) {
            return Boolean.class;
        }

        if ("int".equalsIgnoreCase(s)) {
            return Integer.class;
        }

        if ("string".equalsIgnoreCase(s)) {
            return String.class;
        }
        return null;
    }

    public Object getValue(String option) {
        Class optionType = schemaMaps.get(option);

        if (optionType == null) {
            return new UnsupportError("Unspport Error");
        }

        String arg = commandMaps.get(option);
        if (arg == null) {
            return defaultArgMaps.get(option);
        }

        if (optionType.equals(Boolean.class)) {
            return Boolean.parseBoolean(arg);
        }
        if (optionType.equals(Integer.class)) {
            return Integer.parseInt(arg);
        }
        if (optionType.equals(String.class)) {
            return arg;
        }

        return null;
    }

    public Map<String, Class<?>> getShemaMaps() {
        return schemaMaps;
    }

    public Map<String, String> getCommandMaps() {
        return commandMaps;
    }
}
