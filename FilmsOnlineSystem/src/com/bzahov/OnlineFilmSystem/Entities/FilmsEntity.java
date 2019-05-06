package com.bzahov.OnlineFilmSystem.Entities;

import com.bzahov.OnlineFilmSystem.Utils.BigDecimalUtils;
import com.bzahov.OnlineFilmSystem.exceptions.MyDataErrorException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "FilmsEntity")
@Table(name = "films", schema = "dbo", catalog = "Filmatics")
public class FilmsEntity {
    private int id;
    private String name;
    private String directorName;
    private String producerName;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal earnings = BigDecimal.ZERO;
    private int watchCount;
    private Set<AccountEntity> allAccountsSet = new HashSet<>();
    private Set<CategoriesEntity> allCategoriesSet = new HashSet<>();

    public static final int DEFAULT_WATCH_COUNT = 0;
    public static final String FILMS_ERROR_MESSAGE = " film Price";

    public FilmsEntity() { }

    public FilmsEntity(String name, String directorName, String producerName, BigDecimal price, Integer watchCount) {
        this.name = name;
        this.directorName = directorName;
        this.producerName = producerName;
        this.price = price;
        this.watchCount = watchCount;
    }

    public FilmsEntity(String name, String directorName, String producerName, BigDecimal price, Integer watchCount, Set<AccountEntity> allAccountsSet, Set<CategoriesEntity> allCategoriesSet) {
        this.name = name;
        this.directorName = directorName;
        this.producerName = producerName;
        this.price = price;
        this.watchCount = watchCount;
        this.allAccountsSet = allAccountsSet;
        this.allCategoriesSet = allCategoriesSet;
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
    @Column(name = "directorName", nullable = true, length = 255)
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Basic
    @Column(name = "producerName", nullable = true, length = 255)
    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic

    @Column(name = "earnings")
    public BigDecimal getEarnings() {
        return earnings;
    }

    private void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    @Basic
    @Column(name = "watchCount")
    public int getWatchCount() {
        return watchCount;
    }

    protected void setWatchCount(int watchCount) {
        this.watchCount = watchCount;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "allFilmsSet")
    public Set<AccountEntity> getAllAccountsSet() {
        return allAccountsSet;
    }

    public void setAllAccountsSet(Set<AccountEntity> accountFilmsById) {
        this.allAccountsSet = accountFilmsById;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)/*cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH})*/
    @JoinTable(name = "filmsCategory",
            joinColumns = {
                        @JoinColumn(name = "filmID",
                                referencedColumnName = "id")}
            , inverseJoinColumns = {
                        @JoinColumn(name = "categoryID",
                                referencedColumnName = "id")})
    public Set<CategoriesEntity> getAllCategoriesSet() {
        return allCategoriesSet;
    }

    public void setAllCategoriesSet(Set<CategoriesEntity> filmsCategoriesById) {
        this.allCategoriesSet = filmsCategoriesById;
    }

    public void registerAccount(final AccountEntity account) throws MyDataErrorException {
        if (account == null || getAllAccountsSet().contains(account)) {
            System.err.println("\n>>Account exist");
            throw new MyDataErrorException();
        }
//TODO
        getAllAccountsSet().add(account);
        HashSet<AccountEntity> accountEntities = new HashSet<>(getAllAccountsSet());
        setAllAccountsSet(accountEntities);
//        getAllAccountsSet().add(account);
    }

    public void watchFilm(final AccountEntity account) throws MyDataErrorException {
        if (account == null) {
            System.err.println("\n >>Error in watch Film");
            throw new MyDataErrorException();
        }
        if (!getAllAccountsSet().contains(account)) {
            System.err.println("\n >>Error account: " + account.getName() + " MUST PAY for the film: " + this.getName());
            throw new MyDataErrorException();
        }

        setWatchCount(++this.watchCount);
    }

    public void addEarnings(BigDecimal newEarnings) throws MyDataErrorException {

        if (BigDecimalUtils.isAmountInvalid(newEarnings, FILMS_ERROR_MESSAGE)){
            throw new MyDataErrorException();
        }

        setEarnings(getEarnings().add(newEarnings));
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, directorName, producerName, price, watchCount);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmsEntity that = (FilmsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(directorName, that.directorName) &&
                Objects.equals(producerName, that.producerName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(watchCount, that.watchCount);
    }

    @Override
    public String toString() {
        return "FilmsEntity{" +
                "name='" + name + '\'' +
                ", directorName='" + directorName + '\'' +
                ", producerName='" + producerName + '\'' +
                ", price=" + price +
                ", watchCount=" + watchCount +
               /* ", allAccountsSet=" + allAccountsSet.toString() +
                ", allCategoriesSet=" + allCategoriesSet.toString() */+
                '}';
    }
}
