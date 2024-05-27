package game.campo;

public class UnidadeArea {
	private boolean temBomba;
	private String pos;
	private boolean descoberto = false;
	private String status;
	private boolean flag;
	public UnidadeArea(int x, int y, boolean temBomba) {
		this.pos = x + "#" + y;
		this.temBomba = temBomba;
	}
	public boolean getTemBomba() {
		return this.temBomba;
	}
	//Usado para setar as bombas que sobraram do algoritmo de seleção
	public void setBomba() {
		this.temBomba = true;
	}
	public boolean getDescoberto() {
		return this.descoberto;
	}
	public void setDescoberto() {
		this.descoberto = true;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	public void setFlag() {
		if(this.flag) this.flag = false;
		else this.flag = true;
	}
	public boolean getFlag() {
		return this.flag;
	}
}
