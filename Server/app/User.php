<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    protected $table = 'users';

    protected $guarded = ['id'];

    public function chatroom()
    {
        return $this->belongsTo('App\Chatroom');
    }

    public function messages()
    {
        return $this->hasMany('App\Message', 'uid');
    }
}
