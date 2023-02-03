/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.samples.petclinic.owner.Owner;

import jakarta.transaction.Transactional;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic", "petclinic", "petclinic");
			System.out.println("Conexion creada");

			////////// Taller 1 //////////
			/*
			 * Statement stmt = null; stmt= conn.createStatement(); String sql
			 * ="SELECT * FROM owners"; ResultSet res = null; if(stmt !=null) res=
			 * stmt.executeQuery(sql); if(res!= null) { while(res.next()) { String id=
			 * res.getString("id"); String name= res.getString("first_name"); String ap=
			 * res.getString("last_name"); String ad= res.getString("address"); String
			 * city= res.getString("city"); String tel= res.getString("telephone");
			 *
			 * System.out.println(" ID: " +id+ " Nombre: " +name+ " Apellidos: " +ap+
			 * " Direccion: " +ad+ " Ciudad: " +city+ " Telefono: " +tel+ "\n" );
			 *
			 * } }
			 */

			///////////////////////////////

			////////// Taller 2.1 //////////
			// Nuevo usuario "a pelo" //
			/*
			 * PreparedStatement ps = null; String
			 * sql="INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES (?,?,?,?,?)"
			 * ; ps= conn.prepareStatement(sql);
			 *
			 * String first_name= "Marcos"; String last_name= "AJ"; String
			 * address="Avda Extremadura"; String city= "Badajoz"; String telephone=
			 * "123456789";
			 *
			 * ps.setString(1,first_name); ps.setString(2,last_name);
			 * ps.setString(3,address); ps.setString(4,city); ps.setString(5,telephone);
			 * ps.executeUpdate(); System.out.println("Se ha añadido nuevo usuario");
			 * ps.close(); //
			 */

			////////// Taller 2.2 //////////
			// Solicita por consola nuevo usuario //

			/*
			 * PreparedStatement ps = null; String sql
			 * ="INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES (?,?,?,?,?)"
			 * ; ps= conn.prepareStatement(sql);
			 *
			 * Scanner entrada= new Scanner(System.in); System.out.println("Nombre: ");
			 * String first_name = entrada.nextLine(); System.out.println("Apellidos: ");
			 * String last_name = entrada.nextLine(); System.out.println("Direccion: ");
			 * String address = entrada.nextLine(); System.out.println("Ciudad: "); String
			 * city = entrada.nextLine(); System.out.println("Telefono: "); String
			 * telephone = entrada.nextLine();
			 *
			 * ps.setString(1,first_name); ps.setString(2,last_name);
			 * ps.setString(3,address); ps.setString(4,city); ps.setString(5,telephone);
			 *
			 * ps.executeUpdate(); System.out.println("Bienvenido! :)"); ps.close();
			 */
			///////////////////////////////

			////////// Taller 3 //////////
			/*
			 * Statement ps = null; ps = conn.createStatement(); Scanner entrada = new
			 * Scanner(System.in); System.out.println("Ciudad: "); String city =
			 * entrada.nextLine();
			 *
			 * String sql = "UPDATE owners SET city= '" + city + " ' WHERE id> 10"; //A
			 * modo de prueba como hay 10 ya registrados, me corresponde un id>10.
			 *
			 * ps.executeUpdate(sql); ps.close(); ///////////////////////////////
			 */
			// prueba
			/*
			 * Statement ps = null; ps= conn.createStatement(); String sql =
			 * "DELETE from owners WHERE id= 13"; ps.executeUpdate(sql); ps.close();
			 */

			////////// Taller 4 //////////
			// Busqueda de personas mismo nombre o apellido //
			/*
			int i=0;
			PreparedStatement ps = null;
			String sql = "SELECT * FROM owners";
			ps = conn.prepareStatement(sql);
			ResultSet res = null;
			if (ps != null)
				res = ps.executeQuery(sql);
			if (res != null) {
				Scanner entrada = new Scanner(System.in);
				System.out.println("Nombre o apellido a buscar: ");
				String compara_name = entrada.nextLine();
				while (res.next()) {
					String name = res.getString("first_name");
					String ap = res.getString("last_name");
					if (compara_name.equals(name) || compara_name.equals(ap)) {
						i++;	
						System.out.println("Nombre: " + name);
						System.out.println("Apellidos: " + ap);
					}
				}
				System.out.println("Se han encontrado " +i+ " personas con igual nombre o apellido");
			}
			*/
			///////////////////////////////
			 
		}
		catch (SQLException e) {
			System.err.println("Error");
			e.printStackTrace();
		}
	}

}
