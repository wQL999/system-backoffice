package utils;
import controller.*;
import view.*;
import model.*;



public class HashTable 
{
	
	Lista<Produto>[] hashMap;
	
	
	
	public HashTable()
	{
		
		
		hashMap = new Lista[26];
		inicializarTabelaHash();
	
	
	}

	
	
	public Produto get(String nome) throws Exception
	{
	
		int posicao = nome.toLowerCase().charAt(0);
		posicao = posicao - 97;
		
		
		for(int i = 0; i < hashMap.length; i++)
		{
			
				if(hashMap[posicao].get(i).getNome().equals(nome))
				{
					return hashMap[posicao].get(i); 
				}
			
			
		}
		
		
		return null;
	}
	
	
	
	
	
	private void inicializarTabelaHash()
	
	{
		int tamanho = hashMap.length;
		
		
		for( int i = 0; i< tamanho; i++)
		{
			hashMap[i] = new Lista<Produto>();
			
		}
	}
	
	public void add(Produto p)
	{
		int posicao = p.hashCode();
		hashMap[posicao].addFirst(p);
		
	}
	
	 
	
}

