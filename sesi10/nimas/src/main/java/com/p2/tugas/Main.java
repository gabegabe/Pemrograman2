package com.p2.tugas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.util.Scanner;
import java.lang.String;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.lang.reflect.InvocationTargetException;

public class Main 
{
    public static void main( String[] args )
    {
		Scanner data = new Scanner(System.in);
		int pilih = 0;
		int pilih2 = 0;
		while(pilih != 4){
			System.out.println("===============================");
			System.out.println("MENU PILIHAN");
			System.out.println("1. Tampil Tabel Mahasiswa");
			System.out.println("2. Input Mahasiswa");
			System.out.println("3. Pencarian Mahasiswa");
			System.out.println("4. Keluar");
			System.out.print("Masukan Pilihan Anda : ");
			pilih = data.nextInt();
			data.nextLine();
			if(pilih == 1){
				System.out.println("===============================");
				try{
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhs","root","");
					String sqlTampil = "SELECT * FROM mhs ";
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					ResultSet rs = psTampil.executeQuery();
					System.out.println("|\tid\t|\tnim\t|\tnama\t|\talamat\t|\tsmt\t|");
					while(rs.next()){
						System.out.print("|\t"+rs.getInt("id")+"\t");
						System.out.print("|\t"+rs.getInt("nim")+"\t");
						System.out.print("|\t"+rs.getString("nama")+"\t");
						System.out.print("|\t"+rs.getString("alamat")+"\t");
						System.out.print("|\t"+rs.getInt("smt")+"\t|\n");
					}
					c.close();
				}catch(SQLException se){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
				System.out.println("===============================");				
			}
			else if(pilih == 2){
				System.out.println("===============================");
				System.out.print("Masukan NIM Mahasiswa : ");
				String nim = data.nextLine();
				System.out.print("Masukan Nama Mahasiswa: ");
				String nama = data.nextLine();
				System.out.print("Masukan Alamat Mahasiswa : ");
				String alamat = data.nextLine();
				System.out.print("Masukan Semester Aktif Mahasiswa : ");
				int smt = data.nextInt();
				
				// try{					
					// final String OLD_FORMAT = "dd-MM-yyyy";
					// final String NEW_FORMAT = "yyyy-MM-dd";

					// String oldDateString = tanggal;
					// String newDateString;

					// SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
					// Date d = sdf.parse(oldDateString);
					// sdf.applyPattern(NEW_FORMAT);
					// newDateString = sdf.format(d);
					
					try{
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhs","root","");
						String query = "INSERT INTO mhs(nim,nama,alamat,smt) values (?,?,?,?)";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1,nama);
						ps.setString(2,alamat);
						ps.executeUpdate();
					}catch(SQLException se){
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
					}
				// }
				// catch(ParseException pe){
					
				// }
				
				System.out.println("===============================");
			}
			else if(pilih == 3){
				System.out.println("===============================");
				System.out.print("Masukan NIM Mahasiswa : ");
				String cari = data.nextLine();
				try{
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhs","root","");
					String sqlTampil = "SELECT * FROM nim ";
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					ResultSet rs = psTampil.executeQuery();
					while(rs.next()){
						if(cari.equals(rs.getInt("nim"))){
							System.out.println("===============================");
							System.out.println("NIM : "+rs.getInt("nim"));
							System.out.println("Nama : "+rs.getString("nama"));
							System.out.println("Alamat : "+rs.getString("alamat"));
							System.out.println("Semester : "+rs.getInt("smt"));
							System.out.println("===============================");
							do{
								System.out.println("MENU");
								System.out.println("===============================");
								System.out.println("1.edit");
								System.out.println("2.hapus");
								System.out.println("3.keluar");
								System.out.println("===============================");
								System.out.print("Pilih : ");
								pilih2 = data.nextInt();
								data.nextLine();
								if(pilih2 == 1){
									System.out.print("Masukan NIM : ");
									int judul = data.nextInt();
									System.out.print("Masukan Nama : ");
									String nama = data.nextLine();
									System.out.print("Masukan Alamat: ");
									String alamat = data.nextLine();
									System.out.print("Masukan Semester Aktif : ");
									int smt = data.nextInt();
									
									// try{					
										// final String OLD_FORMAT = "dd-MM-yyyy";
										// final String NEW_FORMAT = "yyyy-MM-dd";

										// String oldDateString = tanggal;
										// String newDateString;

										// SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
										// Date d = sdf.parse(oldDateString);
										// sdf.applyPattern(NEW_FORMAT);
										// newDateString = sdf.format(d);
										
										// try{
											// Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhs","root","");
											// String query = "UPDATE film SET judul='"+judul+"',tglRelease='"+newDateString+"',rating="+rating+",hargaFilm="+harga+" WHERE judul = '"+cari+"'";
											// PreparedStatement ps = conn.prepareStatement(query);
											// ps.executeUpdate();
										// }catch(SQLException se){
											// Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
										// }
									// }catch(ParseException pe){
										
									// }
									
									System.out.println("===============================");
								}
								else if(pilih2 == 2){
									try{
										Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhs","root","");
										String query = "DELETE FROM mhs WHERE nim = '"+cari+"'";
										PreparedStatement ps = conn.prepareStatement(query);
										System.out.println("Data "+cari+" dalam tabel mhs berhasil dihapus ");
										ps.executeUpdate();
									}catch(SQLException se){
										Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
									}
									System.out.println("===============================");
								}
							}while(pilih2 != 3);
						}
					}
					c.close();
				}catch(SQLException se){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
			}
		}
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
