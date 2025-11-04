package model;

public class Pelanggan {
    private int userId;
    private String namaLengkap;
    private String email;
    private String password;
    private String fotoProfil;
    
    // Constructors
    public Pelanggan() {}
    
    public Pelanggan(String namaLengkap, String email, String password) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
    }
    
    public Pelanggan(int userId, String namaLengkap, String email, String password, String fotoProfil) {
        this.userId = userId;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.fotoProfil = fotoProfil;
    }
    
    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFotoProfil() { return fotoProfil; }
    public void setFotoProfil(String fotoProfil) { this.fotoProfil = fotoProfil; }
    
    @Override
    public String toString() {
        return "Pelanggan{" +
                "userId=" + userId +
                ", namaLengkap='" + namaLengkap + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}