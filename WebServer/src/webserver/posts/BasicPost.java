/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.posts;

import java.lang.reflect.Field;

/**
 *
 * @author MeanC
 */
public class BasicPost {
    public String accessToken;
    public String postType;
    
    public void debug()
    {
        Field[] members = this.getClass().getDeclaredFields();

        for (Field member: members)
        {
            try {
                System.out.println("(" + member.getType().getSimpleName() + ") " + member.getName() + ": " + member.get(this));
            }
            catch(Exception ex){}
        }
    }
}
