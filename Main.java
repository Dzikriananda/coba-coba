import java.util.Scanner;

abstract class Angka 
{
    abstract Angka tambah(Angka angka);
    abstract void cetak();
}

class Desimal extends Angka 

    double nilai;

    Desimal(double nilai) 
    {
        this.nilai = nilai;
    }

    Angka tambah(Angka angka) 
    {
        if (angka instanceof Desimal) 
        {
            return new Desimal(this.nilai + ((Desimal) angka).nilai);
        }
        return null;
    }

    void cetak()
     {
        System.out.printf("%.2f\n", nilai);
    }
}

class Rasional extends Angka 
{
    int pembilang;
    int penyebut;

    Rasional(int pembilang, int penyebut) 
    {
        this.pembilang = pembilang;
        this.penyebut = penyebut;
    }

    Angka tambah(Angka angka) 
    {
        if (angka instanceof Rasional) 
        {
            int penyebutUmum = this.penyebut * ((Rasional) angka).penyebut;
            int pembilangBaru = this.pembilang * ((Rasional) angka).penyebut + ((Rasional) angka).pembilang * this.penyebut;
            int fpb = fpb(pembilangBaru, penyebutUmum);
            return new Rasional(pembilangBaru / fpb, penyebutUmum / fpb);
        }
        return null;
    }

    void cetak()
    {
        System.out.printf("%d %d\n", pembilang, penyebut);
    }

    int fpb(int a, int b) 
    {
        if (b == 0) {
            return a;
        }
        return fpb(b, a % b);
    }
}

class SebelasA
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Angka[] angkaArr = new Angka[n];
        for (int i = 0; i < n; i++) 
        {
            int jenis = sc.nextInt();
            if (jenis == 1) 
            {
                angkaArr[i] = new Desimal(sc.nextDouble() + sc.nextDouble());
            } else 
            {
                int p = sc.nextInt();
                int q = sc.nextInt();
                int r = sc.nextInt();
                int s = sc.nextInt();
                angkaArr[i] = new Rasional(p, q).tambah(new Rasional(r, s));
            }
        }
        int indeksAwal = sc.nextInt();
        int indeksAkhir = sc.nextInt();
        for (int k = indeksAwal - 1; k < indeksAkhir; k++) 
        {
            angkaArr[k].cetak();
        }
    }
}