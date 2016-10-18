/**
 * 
 */

 $(document).ready(function ()
    {
          $("#btnShowModal1 li").click(function (e)
            {
                     var ul = $(this).parent();
                    var index = ul.children().index(this);
               ShowDialog(false,"#btnShowModal1 li");
                e.preventDefault();
            });
            
            $("#btnClose").click(function(e)
            {
                HideDialog("#btnClose");
                e.preventDefault();
            });
    });
       
        function ShowDialog(modal, idButton)
        {
               if(idButton==="#btnShowModal1 li")
                {
                    $(".popup1>#overlay").show();
                    $(".popup1>#dialog").fadeIn(300);
                
                    if (modal)
                    {
                        $(".popup1>#overlay").unbind("click");
                  
                    }
                    else
                    {
                        $("#overlay").click(function (e)
                        {
                            HideDialog("#btnClose");
                            e.preventDefault();
                        });
                    }
                }
                
                
            
        }
   
        function HideDialog(btnClose)
        {
            if(btnClose==="#btnClose")
            {
                $(".popup1>#overlay").hide();
                $(".popup1>#dialog").fadeOut(300);
            }
        } 

        /**********
        function startRefresh() 
        {
            clearInterval(6000);
             setTimeout(startRefresh,6000);
        }
       $(document).ready(function ()
    { ***********/
