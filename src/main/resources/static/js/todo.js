/**
 * Created by sumeet on 8/1/15.
 */
'use strict';

// get away from ERB=XML like tags
_.templateSettings.interpolate = /\{\{(.+?)\}\}/g;
_.templateSettings.escape = /\{\{-(.+?)\}\}/g;

var app = {}; // create namespace for our app

app.Todo = Backbone.Model.extend({
    defaults: {
        title: '',
        completed: false
    }
});

app.TodoList = Backbone.Collection.extend({
    model: app.Todo,
    localStorage: new Store("backend-todo")
});

app.toDoList = new app.TodoList();

app.ToDoView = Backbone.View.extend({
    tagName: 'li',
    template: _.template($('#item-template').html()),
    render: function(){
        this.$el.html(this.template(this.model.toJSON()));
        this.input = this.$('.edit');
        return this;
    },
    initialize: function() {
        this.model.on('change', this.render, this);
    },
    events: {
        'dblclick label': 'edit',
        'keypress .edit': 'updateOnEnter',
        'blur .edit': 'close'
    },
    edit: function() {
        this.$el.addClass('editing');
        this.input.focus();
    },
    updateOnEnter: function(e) {
        if (e.which === 13) {
            this.close();
        }
    },
    close: function() {
        var t = this.input.val().trim();
        if (t) {
            this.model.save({ title: t });
        }
        this.$el.removeClass('editing');
    }
});

app.AppView = Backbone.View.extend({
    el: '#todoapp',
    initialize: function() {
        this.input = this.$('#new-todo');
        app.toDoList.on('add', this.addOne, this);
        app.toDoList.on('reset', this.addAll, this);
        app.toDoList.fetch();
    },
    events: {
        'keypress #new-todo': 'createToDoOnEnter'
    },
    createToDoOnEnter: function(e) {
        if (e.which !== 13 || !this.input.val().trim() ) {
            return;
        }
        app.toDoList.create(this.newAttributes());
        this.input.val('');
    },
    addOne: function(todo) {
        var view = new app.ToDoView({model: todo});
        $('#todo-list').append(view.render().el);
    },
    addAll: function() {
        this.$('#todo-list').html('');
        app.toDoList.each(this.addOne, this);
    },
    newAttributes: function() {
        return {
            title: this.input.val().trim(),
            completed: false
        }
    }
});

app.appView = new app.AppView();