package com.sdx.utils.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class SortMaintainSet<K> implements Set<K>
{
	private LinkedList<K> list = null;

	SortMaintainSet(LinkedList<K> list)
	{
		this.list = list;
	}

	@Override
	public int size()
	{
		return list.size();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return list.contains(o);
	}

	@Override
	public Iterator<K> iterator()
	{
		return list.iterator();
	}

	@Override
	public Object[] toArray()
	{
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return list.toArray(a);
	}

	@Override
	public boolean add(K e)
	{
		throw new UnsupportedOperationException("该集合无法被更改");
	}

	@Override
	public boolean remove(Object o)
	{
		throw new UnsupportedOperationException("该集合无法被更改");
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends K> c)
	{
		throw new UnsupportedOperationException("该集合无法被更改");
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return this.list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		throw new UnsupportedOperationException("该集合无法被更改");
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException("该集合无法被更改");
	}
}