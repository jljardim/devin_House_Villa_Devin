package dev.in.villaDevin.exeptions;

public class MoradorNotFoundExcetion extends Exception{
	private static final long serialVersionUID = 1L;

	public MoradorNotFoundExcetion() {
		super("Não foram encontrados Vingadores no Banco");
	}
	
}
