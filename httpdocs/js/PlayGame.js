/*
route: start
text: Hello there.
text: This a sample test.
text: "Quotes" 'Should' '"work'".
text: <b>Bold</b> <i>italic</i>
choice: continue | '"This choice should just continue normally'"" ~ divert | test's"a | this 'diverts to a different route"" ~ speak | "text"| this just prints "text"
choice: continue | '"This choice should just continue normally'"" ~ divert | test's"a | this 'diverts to a different route"" ~ speak | "text"| this just prints "text"
text: boi
end: start

route: test's"a
text: It Works!
end: test's"a
*/

lock_log = false;


function update_progress(override=false) {
  if (window.XMLHttpRequest) {
     // code for modern browsers
     xmlhttp = new XMLHttpRequest();
   } else {
     // code for old IE browsers
     xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
  }
  
  xmlhttp.open('POST', '/update_progress', true);
  xmlhttp.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
  xmlhttp.send($.param(
    {
      'currentSection': current_section,
      'currentStatement': current_statement,
      'gameId': game_id,
      'lastText': document.getElementById('game-text').innerHTML,
      'lastImage': document.getElementById('game-image').src,
      'override': override,
      'log': document.getElementById('log').innerHTML
    }
  ));
}


function update_log(str, set=false) {
  if (lock_log) {
    return;
  }
  
  if (set) {
    document.getElementById('log').innerHTML = str;
  } else {
    document.getElementById('log').innerHTML += str + '<br>';
  }
}


function choice_continue() {
  play_game();
}


function build_choice(choice, callback) {
  a = document.createElement('a');
  a.href = '#choice';
  a.style = 'display: inline-block;padding-right: 15px;';
  a.innerHTML = '<p>' + choice + '</p>';
  a.addEventListener('click', callback);
  return a;
}


function set_choices(choices) {
  document.getElementById('game-choices').innerHTML = '';
  
  for (i = 0; i < choices.length; i++) {
    document.getElementById('game-choices').appendChild(choices[i]);
  }
}


function choice_divert(jump_to) {
  current_section = jump_to;
  current_statement = 0;
  play_game();
}


function choice_speak(str) {
  document.getElementById('game-text').innerHTML = str;
  set_choices([build_choice("-->", play_game)]);
  update_log(str);
  update_progress(override=true);  // we have to update progress here again to make sure it works
}


function new_game() {
  lock_log = false;
  document.getElementById('game-image').src = '../img/game.png';
  document.getElementById('game-text').innerHTML = '';
  update_log('<i>The log helps you keep track of your gameplay.<hr/></i><br>', true);
  current_statement = 0;
  current_section = 'start';
  play_game();
}


function continue_game() {
 if (last_text) {
    document.getElementById('game-text').innerHTML = last_text;
  }
  
  if (last_image) {
    document.getElementById('game-image').src = last_image;
  }

  if(log) update_log(log, true);
  
  if (override) {
    set_choices([build_choice("-->", play_game)]);
  } else {
    if (log) lock_log = true;
    play_game();
  }
}


function play_game() {
  done = false;
  
  if (current_statement >= game[current_section].length) {
    update_log('The End!');
    update_progress();
    
    document.getElementById('game-text').innerHTML = 'The End!';
    document.getElementById('game-choices').innerHTML = '';
    set_choices([build_choice("Play again?", new_game)]);
    
    return;
  }
  
  while (!done) {
    stmt = game[current_section][current_statement];
    
    if (stmt['action'] == 'image') {
      document.getElementById('game-image').src = stmt['args'];
    } else if (stmt['action'] == 'text') {
      document.getElementById('game-text').innerHTML = stmt['args'];
      update_log(stmt['args']);
      
      if (current_statement + 1 >= game[current_section].length || game[current_section][current_statement + 1]['action'] != 'choice') {
        set_choices([build_choice("-->", play_game)]);
        done = true;
      }
    } else if (stmt['action'] == 'choice') {
      choices = [];
      
      for (i = 0; i < stmt['args'].length; i++) {
        if (stmt['args'][i]['action'] == 'continue') {
          x = stmt['args'][i]['choice'];
          choices.push(build_choice(stmt['args'][i]['choice'], function(){update_log(' --> ' + x); choice_continue();}));
        } else if (stmt['args'][i]['action'] == 'divert') {
          y = stmt['args'][i]['choice'];
          target = stmt['args'][i]['target'];
          choices.push(build_choice(stmt['args'][i]['choice'], function(){update_log(' --> ' + y); choice_divert(target);}));
        } else if (stmt['args'][i]['action'] == 'speak') {
          z = stmt['args'][i]['choice'];
          txt = stmt['args'][i]['text'];
          choices.push(build_choice(stmt['args'][i]['choice'], function(){update_log(' --> ' + z); choice_speak(txt);}));
        }
      }

      set_choices(choices);
      done = true;
    }
    
    lock_log = false;
    update_progress();
    current_statement++;
  }
  
}


$('#log-modal').on('show.bs.modal', function (event) {
  var modal = $(this)
  document.getElementById('modal-body').innerHTML = document.getElementById('log').innerHTML;
})


document.body.onkeyup = function(e){
  if (String.fromCharCode(e.keyCode) == 'L') {
    $('#log-modal').modal('toggle')
  }
};
