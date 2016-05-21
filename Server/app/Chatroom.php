<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Chatroom extends Model
{
	protected $table = 'chatroom';

    protected $guarded = ['id'];

    public function messages()
    {
        return $this->hasMany('App\Message', 'cid');
    }
}
