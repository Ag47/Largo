<?php

namespace Api\Transformers;

use App\Chatroom;
use League\Fractal\TransformerAbstract;

class ChatroomTransformer extends TransformerAbstract
{
	public function transform(Chatroom $chatroom)
	{
		return [
			'id' 	=> (int) $chatroom->id,
			'name'  => $chatroom->name
		];
	}
}