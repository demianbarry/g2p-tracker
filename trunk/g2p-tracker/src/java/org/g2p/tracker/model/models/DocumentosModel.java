/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.g2p.tracker.model.models;

import org.g2p.tracker.model.entities.DocumentosEntity;

/**
 *
 * @author kristian
 */
public class DocumentosModel extends BaseModel{

    public DocumentosModel(){
        super(DocumentosEntity.class);
    }

    public static DocumentosEntity findEntityByPK(Object pk) {
        return (DocumentosEntity) BaseModel.findEntityByPK(pk, DocumentosEntity.class);
    }
}
