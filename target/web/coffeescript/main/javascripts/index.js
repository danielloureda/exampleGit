(function() {
  $(function() {
    return $.get("/persons", function(persons) {
      return $.each(persons, function(index, person) {
        $("#persons").append($("<li>").text(person.name));
        return $("#persons").append('<form action="/person/' + person.id + '/delete" method="POST"><input type="submit" value="Delete"></form>');
      });
    });
  });

}).call(this);

//# sourceMappingURL=index.js.map
