package com.bzahov.OnlineFilmSystem.Entities;

import com.bzahov.OnlineFilmSystem.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.bzahov.OnlineFilmSystem.Utils.BigDecimalUtils.isAmountInvalid;

@Entity
@Table(name = "account", schema = "dbo", catalog = "Filmatics")
public class AccountEntity {
    private int id;
    private String name;
    private String email;
    private BigDecimal depositAmount = BigDecimal.ZERO;
    private String passwordHash;
    private Set<FilmsEntity> allFilmsSet = new HashSet<>();

    public static final BigDecimal DEFAULT_ACCOUNT_AMOUNT = BigDecimal.ZERO;

    public AccountEntity() {
    }

    public AccountEntity(String name, String email, BigDecimal depositAmount, Set<FilmsEntity> allFilmsSet) {
        this.name = name;
        this.email = email;
        this.depositAmount = depositAmount;
        this.allFilmsSet = allFilmsSet;
    }

    public AccountEntity(String name, String email, BigDecimal depositAmount) {
        this.name = name;
        this.email = email;
        this.depositAmount = depositAmount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "depositAmount", nullable = true)
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    private void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Basic
    @Column(name = "passwordHash", nullable = true, length = 50)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(depositAmount, that.depositAmount) &&
                //Objects.equals(accountFilmsId, that.accountFilmsId) &&
                Objects.equals(passwordHash, that.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, depositAmount, /*accountFilmsId,*/ passwordHash);
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "accountFilms",
            joinColumns = {@JoinColumn(name = "accountID")},
            inverseJoinColumns = {@JoinColumn(name = "filmID")})
    public Set<FilmsEntity> getAllFilmsSet() {
        return allFilmsSet;
    }

    public void setAllFilmsSet(Set<FilmsEntity> accountFilmsById) {
        this.allFilmsSet = accountFilmsById;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", depositAmount=" + depositAmount +
                ", allFilmsSet=" + allFilmsSet +
                '}';
    }

    public void depositAmount(BigDecimal amount) throws MyDataErrorException {
        if (isAmountInvalid(amount, "Account deposit")) throw new MyDataErrorException();

        setDepositAmount(this.depositAmount.add(amount));
    }

    public void withdrawAmount(BigDecimal amount) throws MyDataErrorException {
        if (isAmountInvalid(amount,"Account Withdraw")) throw new MyDataErrorException();

        if (this.depositAmount.compareTo(amount) >= 0) {

            setDepositAmount(this.depositAmount.subtract(amount));
        } else System.err.println("\n>>Error in withdraw Amount, not enough money in account");
    }

    public void registerFilm(FilmsEntity film) throws MyDataErrorException {
        if (film == null || getAllFilmsSet().contains(film)) {
            throw new MyDataErrorException();
        }
        getAllFilmsSet().add(film);
    }
}
