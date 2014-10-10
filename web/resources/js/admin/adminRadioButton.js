 function initialiseAddPackage(){
               document.getElementById('form:nouvel').style.visibility = 'hidden';
               document.getElementById('form:liste').style.visibility = 'hidden';
               
        }  
        function initialiseAddInsurance(){
            document.getElementById('forminsurance:nouvelinsurance').style.visibility = 'hidden';
            document.getElementById('forminsurance:listeinsurance').style.visibility = 'hidden';
        }
            
        function azerty(){
          
           
           if(document.getElementById('form:radiopack:1').checked){
             //alert("liste coché");
             
               document.getElementById('form:nouvel').style.visibility = 'hidden';

               document.getElementById('form:liste').style.visibility = 'visible';
           }
           if(document.getElementById("form:radiopack:0").checked){
               //alert("ajout coché");
               document.getElementById('form:liste').style.visibility = 'hidden';

               document.getElementById('form:nouvel').style.visibility = 'visible';

           }
       }
       function azerty2(){
           //alert("azerty");
           
           if(document.getElementById('forminsurance:radio:1').checked){
             
               
               document.getElementById('forminsurance:nouvelinsurance').style.visibility = 'hidden';

               document.getElementById('forminsurance:listeinsurance').style.visibility = 'visible';
           }
           if(document.getElementById("forminsurance:radio:0").checked){
               //alert("ajout coché");
               document.getElementById('forminsurance:listeinsurance').style.visibility = 'hidden';

               document.getElementById('forminsurance:nouvelinsurance').style.visibility = 'visible';

           }
           }