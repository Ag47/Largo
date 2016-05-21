<?php

namespace Api\Controllers;

use App\User;
use App\Http\Requests;
use Illuminate\Http\Request;
use Api\Transformers\UserTransformer;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\Validator;

class UserController extends BaseController
{
    public function index()
    {
        return $this->collection(User::all(), new UserTransformer);
    }

    public function store(Request $request)
    {
        $rules = [
            'name' => 'required|max:255',
            'email' => 'required|email',
            'profile_image' => 'mimes:jpeg,bmp,png'
        ];
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        $user = new User;
        $user->name = $request->name;
        $user->email = $request->email;
        $image = Input::file('image');
        if($image){
            $filename = time() . '-' . 'profile';
            $extension = $image->getClientOriginalExtension();
            $image->move('profile/', $filename . '.' . $extension);
            $user->profile_image = asset('profile/'.$filename . '.' . $extension);
        }
        $user->save();
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success'
            ,'data' => $user]
            , 200);
    }

    public function show($id)
    {
        return $this->item(User::findOrFail($id), new UserTransformer);
    }

    public function update(Request $request, $id)
    {
        $rules = [
            'name' => 'required|max:255',
            'email' => 'required|email',
            'profile_image' => 'mimes:jpeg,bmp,png'
        ];
        $v = Validator::make($request->all(), $rules);
        $message = [];
        if ($v->fails()) {
            $vm = $v->messages()->getMessages();
            
            foreach ($vm as $m) {
                array_push($message, $m[0]);
            }
        }
        if($message){
            return response()->json([
                'resultCode' => 10000
                ,'message' => $message[0]]
                , 400);
        }

        $user = User::findOrFail($id);
        $user->name = $request->name;
        $user->email = $request->email;
        $image = Input::file('image');
        if($image){
            $filename = time() . '-' . 'profile';
            $extension = $image->getClientOriginalExtension();
            $image->move('profile/', $filename . '.' . $extension);
            $user->profile_image = asset('profile/'.$filename . '.' . $extension);
        }
        $user->save();
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success'
            ,'data' => $user]
            , 200);
    }

    public function destroy($id)
    {
        User::destroy($id);
        return response()->json([
            'resultCode' => 0
            ,'message' => 'Success']
            , 200);
    }
}
