package org.epoxide.ld44.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NamedRegistry<V extends IRegisterable<V>> implements Iterable<V> {

    private Map<Identifier, V> values = new HashMap<Identifier, V>();
    
    public Set<Identifier> getIdentifiers () {

        return this.values.keySet();
    }

    public List<Identifier> getIdentifiers (String domain) {

        final List<Identifier> results = new ArrayList<Identifier>();

        for (final Identifier id : this.values.keySet()) {
            if (id.getDomain().equals(domain)) {
                results.add(id);
            }
        }
        
        return results;
    }

    public V getValue (Identifier identifier) {

        return this.values.get(identifier);
    }
    
    public List<V> getValues () {

        return new ArrayList<V>(this.values.values());
    }
    
    public boolean hasIdentifier (Identifier identifier) {

        return this.values.containsKey(identifier);
    }

    public V registerValue (Identifier identifier, V value) {

        this.values.put(identifier, value);
        return value;
    }
    
    public V registerValue (String domain, String name, V value) {

        return this.registerValue(new Identifier(domain, name), value);
    }
    
    public V registerValue (String identifier, V value) {

        return this.registerValue(new Identifier(identifier), value);
    }
    
    public V registerValue (V value) {

        return this.registerValue(value.getIdentifier(), value);
    }

    @Override
    public Iterator<V> iterator () {

        return this.values.values().iterator();
    }
}