package game.campo;

import java.util.Random;
import java.util.Scanner;

public class Campo {
	private UnidadeArea area[][];
	private double fatorDificuldade;
	private int bombasSobrando;
	private int qtBombas;
	private boolean perdeu;
	private boolean ganhou;
	public int getBombasSobrando() {
		return this.bombasSobrando;
	}
	public boolean getPerdeu() {
		return this.perdeu;
	}
	public Campo(int tamanho, int dificuldade) {
		if(dificuldade >= 1 && dificuldade <= 3) {
			Random random = new Random();
			Double probabilidade;
			this.area = new UnidadeArea[tamanho][tamanho];
			switch(dificuldade) {
				case 1: 
					this.bombasSobrando = (tamanho * tamanho) / 4;
					this.qtBombas = this.bombasSobrando;
					this.fatorDificuldade = 0.8;
					break;
				case 2: 
					this.bombasSobrando = (tamanho * tamanho) / 3;
					this.qtBombas = this.bombasSobrando;
					this.fatorDificuldade = 0.7;
					break;
				case 3: 
					this.bombasSobrando = (tamanho * tamanho) / 2;
					this.qtBombas = this.bombasSobrando;
					this.fatorDificuldade = 0.6;
					break;			
			}
			for (int x = 0; x < area.length; x++) {
				for(int y = 0; y < area.length; y++) {
					probabilidade = random.nextDouble();
					if(probabilidade >= this.fatorDificuldade && this.bombasSobrando > 0) {						
						area[x][y] = new UnidadeArea(x, y, true);
						this.bombasSobrando--;
					}
					else area[x][y] = new UnidadeArea(x, y, false);
				}
			}
			while(this.bombasSobrando > 0) {
				for (int x = 0; x < area.length; x++) {
					for(int y = 0; y < area.length; y++) {
						probabilidade = random.nextDouble();
						if(probabilidade >= this.fatorDificuldade && this.bombasSobrando > 0 && !this.area[x][y].getTemBomba()) {						
							area[x][y].setBomba();
							this.bombasSobrando--;
						}
					}
				}
			}
		} 
	}
	public String testeCampo() {
		String campo = "";
		for(int x = 0; x < this.area.length; x++) {
			for(int y = 0; y < this.area.length; y++) {
				if(this.area[x][y].getTemBomba()) {
					if(y == this.area.length - 1) {
						campo += "Y\n";
					}
					else campo += "Y ";
				}
				else {
					if(y == this.area.length - 1) {
						campo += "O\n";
					}
					else campo += "O ";
				}
			}
		}
		return campo;
	}
	
	public String mostrarCampo() {
		String campo = "";
		for(int x = 0; x < this.area.length; x++) {
			for(int y = 0; y < this.area.length; y++) {
				if(!this.area[x][y].getDescoberto()) {
					if(this.area[x][y].getFlag()) {
						if(y == this.area.length - 1) campo += "╒\n";
						else campo += "╒ ";
					}
					else {
						if(y == this.area.length - 1) campo += "■\n";
						else campo += "■ ";
					}
				}
				else {
					if(y == this.area.length - 1) campo += this.area[x][y].getStatus() + "\n";
					else campo += this.area[x][y].getStatus() + " ";
				}
			}
		}
		return campo;
	}
	
	public void jogar(int x, int y) {
		if(this.area[x][y].getTemBomba()) {
			this.gameOver();
		}
		else {
			if(!this.area[x][y].getDescoberto()) {
				this.area[x][y].setDescoberto();
				int bombasAoRedor = this.bombasAoRedor(x, y);
				if(bombasAoRedor == 0) {
					this.descobrir(x, y);
				}
				else {
					this.area[x][y].setStatus("" + this.bombasAoRedor(x, y));
					for(int i = x - 1; i <= x + 1; i++) {
						for(int j = y - 1; j <= y + 1; j++) {
							if(i == x && j == y) continue;
							else {
								if(i >= 0 && i < this.area.length && j >= 0 && j < this.area.length) {
									if(this.bombasAoRedor(j, j) == 0) this.descobrir(i, j);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public int bombasAoRedor(int x, int y) {
		int qt = 0;
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				if(j == y && i == x) continue;
				else {
					if(i >= 0 && i < this.area.length && j >= 0 && j < this.area.length) {
						if(this.area[i][j].getTemBomba()) qt++;						
					}
				}
			}
		}
		return qt;
	}
	//Ao chamar este método, os parâmetros indicam um local sem bomba ao redor, necessariamente
	public void descobrir(int x, int y) {
		this.area[x][y].setDescoberto();
		this.area[x][y].setStatus("0");
		for(int i = x - 1; i <= x + 1; i++) {
			for(int j = y - 1; j <= y + 1; j++) {
				if(i >= 0 && i < this.area.length && j >= 0 && j < this.area.length) {
					if(this.bombasAoRedor(i, j) == 0 && !this.area[i][j].getDescoberto()) descobrir(i, j);
					else {
						this.area[i][j].setStatus("" + this.bombasAoRedor(i, j));
						this.area[i][j].setDescoberto();
					}
				}
			}
		}
	}
	
	public void checarVitoria() {
		if(!this.perdeu) {			
			int qtCobertos = 0;
			for(int x = 0; x < this.area.length; x++) {
				for(int y = 0; y < this.area.length; y++) {
					if(!this.area[x][y].getDescoberto()) qtCobertos++;
				}
			}
			if(qtCobertos == this.qtBombas) this.ganhou = true;
		}
	}
	
	public void gameOver() {
		this.perdeu = true;
		for(int x = 0; x < this.area.length; x++) {
			for(int y = 0; y < this.area.length; y++) {
				if(this.area[x][y].getTemBomba()) {
					this.area[x][y].setStatus("X"); 
					this.area[x][y].setDescoberto(); 
				}
			}
		}
	}
	
	public void init() {
		Scanner s = new Scanner(System.in);
		String pos; int x; int y;
		while(!this.perdeu && !this.ganhou) {
			System.out.println(this.mostrarCampo());
			System.out.println("Faça a sua jogada: X#Y#[J OU F]");
			pos = s.nextLine();
			x = Integer.parseInt(pos.split("#")[0]);
			y = Integer.parseInt(pos.split("#")[1]);
			if(pos.split("#")[2].equalsIgnoreCase("f") && !this.area[x][y].getDescoberto()) this.area[x][y].setFlag();
			else{
				this.jogar(x, y);
				this.checarVitoria();
			}
		}
		System.out.println(this.mostrarCampo());
		if(this.ganhou) {
			System.out.println("Você ganhou!");
		}
		else {
			System.out.println("Você perdeu!");
		}
	}
}
