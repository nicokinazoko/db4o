
package db4o;


public class Pegawai {
    public String _nip, _nama;
    public int _umur, _gaji, _no_hp;
    
    public Pegawai() {}
    public Pegawai(String nip, String nama, int umur, int gaji, int no_hp ){
        this._nip    =   nip;
        this._nama   =   nama;
        this._umur   =   umur;
        this._gaji   =   gaji;
        this._no_hp  =   no_hp;
    }

    public String getNip() {
        return _nip;
    }

    public void setNip(String _nip) {
        this._nip = _nip;
    }

    public String getNama() {
        return _nama;
    }

    public void setNama(String _nama) {
        this._nama = _nama;
    }

    public int getUmur() {
        return _umur;
    }

    public void setUmur(int _umur) {
        this._umur = _umur;
    }

    public int getGaji() {
        return _gaji;
    }

    public void setGaji(int _gaji) {
        this._gaji = _gaji;
    }

    public int getNo_hp() {
        return _no_hp;
    }

    public void setNo_hp(int _no_hp) {
        this._no_hp = _no_hp;
    }




    public String toString()
    {
    return "[" + _nip + ";"+ _nama + ";" + _no_hp + ";" + _umur + ";" + _gaji + ";"+"]";
    }
    
}
