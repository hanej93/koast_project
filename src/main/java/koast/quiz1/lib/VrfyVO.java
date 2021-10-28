package koast.quiz1.lib;

public class VrfyVO {
	
	public VrfyVO(String d, String centre, String sc, String dom, String par, Integer t, Integer s, Double v) {
		super();
		this.d = d;
		this.centre = centre;
		this.sc = sc;
		this.dom = dom;
		this.par = par;
		this.t = t;
		this.s = s;
		this.v = v;
	}

	private String d;
	
	private String centre;
	
	private String sc;
	
	private String dom;
	
	private String par;
	
	private Integer t;
	
	private Integer s;
	
	private Double v;

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getCentre() {
		return centre;
	}

	public void setCentre(String centre) {
		this.centre = centre;
	}

	public String getSc() {
		return sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getPar() {
		return par;
	}

	public void setPar(String par) {
		this.par = par;
	}

	public Integer getT() {
		return t;
	}

	public void setT(Integer t) {
		this.t = t;
	}

	public Integer getS() {
		return s;
	}

	public void setS(Integer s) {
		this.s = s;
	}

	public Double getV() {
		return v;
	}

	public void setV(Double v) {
		this.v = v;
	}

	@Override
	public String toString() {
		return "VrfyVO [d=" + d + ", centre=" + centre + ", sc=" + sc + ", dom=" + dom + ", par=" + par + ", t=" + t
				+ ", s=" + s + ", v=" + v + "]";
	}
}
