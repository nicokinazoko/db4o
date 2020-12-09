package db4o;

import java.io.File;
import java.util.*;
import com.db4o.*;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import com.db4o.query.*;

public class db40Pegawai {

    public static void main(String[] args) {

        new File("complete.yap").delete(); // reset the database
        Db4o.configure().messageLevel(0); // 0=silent, 3=loud
        ObjectContainer db = Db4o.openFile("complete.yap");
        try {

            db.store(new Pegawai("123", "Nico Nico", 19, 3000000, 387157));
            db.store(new Pegawai("124", "Nico", 30, 4000000, 387684));
            db.store(new Pegawai("125", "Ahmad", 40, 3500000, 349532));
            db.store(new Pegawai("126", "Beni", 18, 400000, 878532));
            db.store(new Pegawai("127", "Cony", 25, 2000000, 432431));

            ObjectSet result = (ObjectSet) db.queryByExample(new Pegawai());
            listResult(result); // get all

//            retrieveAllPegawai(db);
//            hapusPegawaiByNip(db);
//            updateNoHpPegawaiByNip(db);
//            updateGajiPegawaiByNip(db);
//            updateUmurPegawaiByNip(db);
//            updateNamaPegawaiByNip(db);
//            updateNipPegawaiByNama(db);
//            retrieveByUmurMore20(db);
//            retrieveByGajiLess4M(db);
            retrieveByNamaPegawai(db);
            retrieveByNipPegawai(db);

        } finally {
            db.close();
        }

    }

    public static void listResult(ObjectSet result) {
        while (result.hasNext()) {
            System.out.println(result.next());
        }
        System.out.println("---------------");
    }

    public static void retrieveAllPegawai(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Pegawai.class);
        ObjectSet result = query.execute();
        System.out.println("retrieveAllPegawai = ");
        listResult(result);
    }

    public static void hapusPegawaiByNip(ObjectContainer db) {

        Pegawai p = new Pegawai();
        p.setNip("123");
        ObjectSet result = db.queryByExample(p);
        Pegawai found = (Pegawai) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);
        retrieveAllPegawai(db);

    }

    public static void updateNoHpPegawaiByNip(ObjectContainer db) {

        Pegawai p = new Pegawai();
        p.setNip("124");
        ObjectSet result = db.queryByExample(p);
        System.out.println(result);
        Pegawai found = (Pegawai) result.next();
        found.setNo_hp(365843);
        db.store(found);
        System.out.println("Update Umur untuk " + found);
        retrieveAllPegawai(db);
    }

    public static void updateGajiPegawaiByNip(ObjectContainer db) {
        Pegawai p = new Pegawai();
        p.setNip("124");
        ObjectSet result = db.queryByExample(p);
        System.out.println(result);
        Pegawai found = (Pegawai) result.next();
        found.setGaji(4500000);
        db.store(found);
        System.out.println("Update Gaji untuk " + found);
        retrieveAllPegawai(db);
    }

    public static void updateUmurPegawaiByNip(ObjectContainer db) {
        Pegawai p = new Pegawai();
        p.setNip("124");
        ObjectSet result = db.queryByExample(p);
        System.out.println(result);
        Pegawai found = (Pegawai) result.next();
        found.setUmur(40);
        db.store(found);
        System.out.println("Update Umur untuk " + found);
        retrieveAllPegawai(db);
    }

    public static void updateNamaPegawaiByNip(ObjectContainer db) {
        Pegawai p = new Pegawai();
        p.setNip("124");
        ObjectSet result = db.queryByExample(p);
        System.out.println(result);
        Pegawai found = (Pegawai) result.next();
        found.setNama("Nico");
        db.store(found);
        System.out.println("Update Nama untuk " + found);
        retrieveAllPegawai(db);
    }

    public static void updateNipPegawaiByNama(ObjectContainer db) {
        Pegawai p = new Pegawai();
        p.setNama("Nico");
        ObjectSet result = db.queryByExample(p);
        System.out.println(result);
        Pegawai found = (Pegawai) result.next();
        found.setNip("1234567");
        db.store(found);
        System.out.println("Update Nip untuk " + found);
        retrieveAllPegawai(db);
    }

    public static void retrieveByUmurMore20(ObjectContainer db) {
        ObjectSet result = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getUmur() > 20;
            }
        });
        System.out.println("Data Pegawai dengan Umur > 20");
        listNativeQuery(result);
    }

    public static void retrieveByGajiLess4M(ObjectContainer db) {
        ObjectSet result = db.query(new Predicate<Pegawai>() {
            @Override
            public boolean match(Pegawai pegawai) {
                return pegawai.getGaji() < 4000000;
            }
        });
        System.out.println("Data Pegawai dengan Gaji < 4.000.000");
        listNativeQuery(result);
    }

    public static void listNativeQuery(List<Pegawai> pegawai) {
        Iterator iterator = pegawai.iterator();
        System.out.println("--------Hasil Native Query---------");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }
        System.out.println();
    }

    public static void retrieveByNamaPegawai(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Pegawai.class);
        Constraint constraint = query.descend("_nama")
                .constrain("Nico");
        ObjectSet result1 = query.execute();
        System.out.println("Data Pegawai dari Nama");
        listResult(result1);

    }

    public static void retrieveByNipPegawai(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Pegawai.class);
        Constraint constraint = query.descend("_nip")
                .constrain("123");
        ObjectSet result1 = query.execute();
        System.out.println("Data Pegawai dari NIP");
        listResult(result1);

    }

}

//-simpan data pegawai, (clear)
//
//  	  -update data pegawai pada field :
//         		 Nama, NIP (String) (clear)
//          		Nama (String) (Clear)
//         		 Umur (int) (Clear)
//          		Gaji  (int) (clear)
//         		 No.Hp  (int) (clear)
//     	-retrieve  semua Pegawai (Clear)
//    	-retrieve  berdasarkan nama pegawai
//    	-retrieve  berdasarkan NIP pegawai 
//    	-retrieve  berdasarkan Umur>20 pegawai (clear)
//   	 -retrieve  berdasarkan Gaji<4000000 pegawai (clear)
//
//  	  -delete berdasarkan NIP pegawai (clear)

