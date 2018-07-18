package org.kotlinlang.koans.collections;

import java.util.*;

@SuppressWarnings("unused")
public class Strange {
    public Collection<String> doSomethingStrangeWithCollection(
            Collection<String> collection
    ) {
        Map<Integer, List<String>> groupsByLength = new HashMap<>();
        for (String s : collection) {
            List<String> strings = groupsByLength.computeIfAbsent(s.length(), k -> new ArrayList<>());
            strings.add(s);
        }

        int maximumSizeOfGroup = 0;
        for (List<String> group : groupsByLength.values()) {
            if (group.size() > maximumSizeOfGroup) {
                maximumSizeOfGroup = group.size();
            }
        }

        for (List<String> group : groupsByLength.values()) {
            if (group.size() == maximumSizeOfGroup) {
                return group;
            }
        }
        return null;
    }
}
