<?php

namespace Api\Transformers;

use App\Message;
use League\Fractal\TransformerAbstract;

class MessageTransformer extends TransformerAbstract
{
	public function transform(Message $message)
	{
		return [
			'id' 	=> (int) $message->id,
			'uid'   => $message->uid,
			'text'	=> $message->text,
			'image' => $message->image,
			'video' => $message->video,
			'send_time' => $message->send_time
		];
	}
}