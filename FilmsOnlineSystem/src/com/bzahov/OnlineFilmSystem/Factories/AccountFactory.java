package com.bzahov.OnlineFilmSystem.Factories;

import com.bzahov.OnlineFilmSystem.Entities.AccountEntity;
import com.bzahov.OnlineFilmSystem.Entities.FilmsEntity;
import com.bzahov.OnlineFilmSystem.exceptions.MyDataErrorException;
import com.bzahov.SessionHolder;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Set;

import static com.bzahov.OnlineFilmSystem.Entities.AccountEntity.DEFAULT_ACCOUNT_AMOUNT;
import static com.bzahov.OnlineFilmSystem.Utils.BigDecimalUtils.isAmountInvalid;

public class AccountFactory {

    public static AccountEntity createAccount(final String name,
                                              final String email) throws MyDataErrorException {

        return createAccountEntity(name, email, DEFAULT_ACCOUNT_AMOUNT, null);
    }

    public static AccountEntity createAccountWithFilmSet(final String name,
                                                         final String email,
                                                         final Set<FilmsEntity> filmsSet) throws MyDataErrorException {
        return createAccountEntity(name, email, DEFAULT_ACCOUNT_AMOUNT, filmsSet);
    }

    private static AccountEntity createAccountEntity(final String name,
                                                     final String email, BigDecimal amount,
                                                     final Set<FilmsEntity> filmsSet) throws MyDataErrorException {

        if (isAmountInvalid(amount,"create Account")) throw new MyDataErrorException();

        final Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();

        AccountEntity account = new AccountEntity(name, email, amount);
        if (filmsSet != null) {
            account.setAllFilmsSet(filmsSet);
            filmsSet.forEach(filmsEntity -> filmsEntity.getAllAccountsSet().add(account));
        }
        session.save(filmsSet);
        session.save(account);
        session.getTransaction().commit();
        return account;
    }

    public static void depositMoney(AccountEntity account, BigDecimal amount) {
        final Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();
        try {
            account.depositAmount(amount);

            session.save(account);
            session.getTransaction().commit();
        } catch (MyDataErrorException e) {
            session.getTransaction().rollback();
        }
    }

    public static void withdrawMoney(AccountEntity account, BigDecimal amount) {
        final Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();
        try {
            account.withdrawAmount(amount);
            session.save(account);
            session.getTransaction().commit();
        } catch (MyDataErrorException e) {
            session.getTransaction().rollback();
        }
    }

    public static void registerForFilm(AccountEntity account, FilmsEntity film) {
        final Session session = SessionHolder.getINSTANCE();

        session.beginTransaction();
        try {
            account.withdrawAmount(film.getPrice());
            film.addEarnings(film.getPrice());
            account.registerFilm(film);
            film.registerAccount(account);
            session.save(account);
            session.save(film);
            session.getTransaction().commit();
        } catch (MyDataErrorException e) {
            System.out.println("\nPeshooo\n");
            //account.getAllFilmsSet().forEach(FilmsEntity::toString);
            //film.getAllAccountsSet().forEach(AccountEntity::toString);
            session.getTransaction().rollback();
        }
    }

    public static void watchFilm(AccountEntity account, FilmsEntity film){
        final Session session = SessionHolder.getINSTANCE();

        session.beginTransaction();
        try {
            film.watchFilm(account);
            session.save(film);
            session.getTransaction().commit();
        } catch (MyDataErrorException e) {
            session.getTransaction().rollback();
        }
    }

}
