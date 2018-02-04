package io.github.nanodankster.skriptnpc.util;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.util.Pair;
import ch.njol.yggdrasil.Fields;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// I stopped writing Java after this class.
public class EnumClassInfo<E extends Enum<?>> {
    private Class<E> eClass;
    private String codeName;
    private List<Map.Entry<String, E>> pairings = new ArrayList<>();

    public EnumClassInfo(Class<E> eClass, String codeName) {
        this.eClass = eClass;
        this.codeName = codeName;
        for (E value : eClass.getEnumConstants()) {
            pairings.add(0, new Pair<>(value.toString(), value));
        }
    }

    public ClassInfo<E> getClassInfo() {
        return new ClassInfo<>(eClass, codeName)
                .user(codeName + "s?")
                .parser(getParser(true))
                .serializer(getSerializer());
    }

    private Parser<E> getParser(Boolean stringEdited) {
        if (stringEdited) {
            return new Parser<E>() {
                @Override
                public E parse(String s, ParseContext context) {
                    s = s.toUpperCase().replace(" ", "_").replace("İ", "I");
                    for (Map.Entry<String, E> pairing : pairings) {
                        if (s.equals(pairing.getKey())) {
                            return pairing.getValue();
                        }
                    }
                    return null;
                }

                @Override
                public String toString(E e, int i) {
                    return e.toString();
                }

                @Override
                public String toVariableNameString(E e) {
                    return e.toString();
                }

                @Override
                public String getVariableNamePattern() {
                    return ".+";
                }
            };
        }
        return new Parser<E>() {
            @Override
            public E parse(String s, ParseContext context) {
                for (Map.Entry<String, E> pairing : pairings) {
                    if (s.equals(pairing.getKey())) {
                        return pairing.getValue();
                    }
                }
                return null;
            }

            @Override
            public String toString(E e, int i) {
                return e.toString();
            }

            @Override
            public String toVariableNameString(E e) {
                return e.toString();
            }

            @Override
            public String getVariableNamePattern() {
                return ".+";
            }
        };

    }

    private Serializer<E> getSerializer() {
        return new Serializer<E>() {
            @Override
            public Fields serialize(E e) {
                Fields fields = new Fields();
                fields.putObject("value", EnumClassInfo.this.toString());
                return fields;
            }

            @Override
            public void deserialize(E e, Fields fields) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean mustSyncDeserialization() {
                return false;
            }

            @Override
            protected boolean canBeInstantiated() {
                return false;
            }

            @Override
            public E deserialize(Fields fields) throws StreamCorruptedException {
                for (Map.Entry<String, E> pairing : pairings) {
                    if (fields.getObject("value").equals(pairing.getKey())) {
                        return pairing.getValue();
                    }
                }
                return null;
            }
        };
    }
}
