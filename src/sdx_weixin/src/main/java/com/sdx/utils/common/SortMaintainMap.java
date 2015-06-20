package com.sdx.utils.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortMaintainMap<K, V> implements Map<K, V>, Iterable<K>
{
	private HashMap<K, V> map = null;
	private LinkedList<K> sortList = null;
	private LinkedList<V> valueList = null;

	public SortMaintainMap()
	{
		map = new HashMap<K, V>();
		sortList = new LinkedList<K>();
		valueList = new LinkedList<V>();
	}

	@Override
	public int size()
	{
		return sortList.size();
	}

	@Override
	public boolean isEmpty()
	{
		return sortList.isEmpty();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return map.containsValue(value);
	}

	@Override
	public V get(Object key)
	{
		return map.get(key);
	}

	@Override
	public V put(K key, V value)
	{
		if (!map.containsKey(key))
		{
			sortList.addLast(key);
			valueList.addLast(value);
		} else {
			int i = sortList.indexOf(key);
			valueList.remove(i);
			valueList.add(i, value);
		}
		return map.put(key, value);
	}

	@Override
	public V remove(Object key)
	{
		if (map.containsKey(key))
		{
			sortList.remove(key);
			valueList.remove(map.get(key));
		}
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		for (K k : m.keySet())
		{
			this.put(k, m.get(k));
		}
	}

	@Override
	public void clear()
	{
		map.clear();
		sortList.clear();
	}

	@Override
	public SortMaintainSet<K> keySet()
	{
		return new SortMaintainSet<K>(this.sortList);
	}

	@Override
	public List<V> values()
	{
	    return getValues();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		LinkedList<Map.Entry<K, V>> entrys = new LinkedList<Map.Entry<K, V>>();
		for (K k : this.sortList)
		{
			entrys.add(new Entry(k, map.get(k)));
		}
		return new SortMaintainSet<Map.Entry<K,V>>(entrys);
	}

	@Override
	public Iterator<K> iterator()
	{
		return new Iterator<K>()
		{
			int i = 0;

			@Override
			public boolean hasNext()
			{
				return i < sortList.size();
			}

			@Override
			public K next()
			{
				return sortList.get(i++);
			}

			@Override
			public void remove()
			{
				sortList.remove(i - 1);
				i--;
			}};
	}

	public List<K> getKeys()
	{
		LinkedList<K> keyList = new LinkedList<K>();
		for (K k : sortList)
		{
		    keyList.add(k);
		}
        return keyList;
	}

	public List<V> getValues()
	{
        LinkedList<V> values = new LinkedList<V>();
        for (V v : valueList)
        {
            values.add(v);
        }
        return values;
	}

	public V insertFirst(K key, V value)
	{
        if (!map.containsKey(key))
        {
            sortList.addFirst(key);
            valueList.addFirst(value);
        }
        return map.put(key, value);
	}

	class Entry implements Map.Entry<K, V>
	{
		private K k;
		private V v;

		Entry(K k, V v)
		{
			this.k = k;
			this.v = v;
		}

		@Override
		public K getKey()
		{
			return k;
		}

		@Override
		public V getValue()
		{
			return v;
		}

		@Override
		public V setValue(V value)
		{
			return v;
		}
	}

	public void sortByKey(Comparator<K> c)
	{
	    Collections.sort(sortList, c);
        resortValueList();
	}

	public void sortByValue(Comparator<V> c)
	{
	    final Comparator<V> valueComparator = c;
	    Collections.sort(sortList, new Comparator<K>()
        {
            @Override
            public int compare(K o1, K o2)
            {
                return valueComparator.compare(get(o1), get(o2));
            }
        });
	    resortValueList();
	}

	private void resortValueList()
	{
	    LinkedList<V> newList = new LinkedList<V>();
	    for (K key : sortList)
	    {
	        newList.add(get(key));
	    }
	    valueList = newList;
	}
}
