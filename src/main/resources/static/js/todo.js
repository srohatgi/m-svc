/**
 * Created by sumeet on 8/1/15.
 */
'use strict';

// get away from ERB=XML like tags
_.templateSettings.interpolate = /\{\{=(.+?)\}\}/g;
_.templateSettings.escape = /\{\{-(.+?)\}\}/g;
_.templateSettings.evaluate = /\{\{(.+?)\}\}/g;

var app = {}; // create namespace for our app

app.Todo = Backbone.Model.extend({
    defaults: {
        title: '',
        completed: false
    },
    toggle: function() {
        this.save({ completed: !this.get('completed') });
    }
});

app.TodoList = Backbone.Collection.extend({
    model: app.Todo,
    localStorage: new Store("backend-todo"),
    completed: function() {
        return this.filter(function( todo ) {
            return todo.get('completed');
        });
    },
    remaining: function() {
        return this.without.apply( this, this.completed() );
    }
});

app.toDoList = new app.TodoList();

app.ToDoView = Backbone.View.extend({
    tagName: 'li',
    template: _.template('<div class="view"> ' +
        '<input class="toggle" type="checkbox" {{= completed ? "checked" : "" }}/> ' +
        '<label>{{- title }}</label> ' +
        '<input class="edit" value="{{- title }}"/> ' +
        '<button class="destroy">remove</button> ' +
        '</div>'),
    render: function(){
        console.log("model: " + JSON.stringify(this.model));
        this.$el.html(this.template(this.model.toJSON()));
        this.input = this.$('.edit');
        return this;
    },
    initialize: function() {
        this.model.on('change', this.render, this);
        this.model.on('destroy', this.remove, this);
    },
    events: {
        'dblclick label': 'edit',
        'keypress .edit': 'updateOnEnter',
        'blur .edit': 'close',
        'click .toggle': 'toggleCompleted',
        'click .destroy': 'destroy'
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
    },
    toggleCompleted: function() {
        this.model.toggle();
    },
    destroy: function() {
        this.model.destroy();
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
        // filter todo item list
        switch(window.filter){
            case 'remaining':
                _.each(app.toDoList.remaining(), this.addOne);
                break;
            case 'completed':
                _.each(app.toDoList.completed(), this.addOne);
                break;
            default:
                app.toDoList.each(this.addOne, this);
                break;
        }
    },
    newAttributes: function() {
        return {
            title: this.input.val().trim(),
            completed: false
        }
    }
});

app.Router = Backbone.Router.extend({
    routes: {
        '*filter' : 'setFilter'
    },
    setFilter: function(params) {
        console.log('app.router.params = ' + params); // just for didactical purposes.
        window.filter = params && params.trim() || '';
        app.toDoList.trigger('reset');
    }
});

app.appView = new app.AppView();
app.router = new app.Router();
Backbone.history.start();
