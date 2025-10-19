/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Emir Alvarado
 */

/*Toda clase que implemente Validable debe definir cómo validar sus datos.
Devuelve boolean:
true si los datos de la clase son correctos.
false si hay algún error o inconsistencia.
*/
public interface Validable {
    boolean validar();
}

